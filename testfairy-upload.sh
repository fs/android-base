#!/bin/sh

# UPDATE THESE
API_KEY=9d3fee392bd23fd649f19e6b4a43b6e5dd4b1b07
TESTER_GROUPS=testers
KEYSTORE=debug.keystore
STOREPASS=android
ALIAS=android

# locations of various tools
CURL=curl
ZIP=zip
ZIPALIGN=zipalign
JARSIGNER=jarsigner
SERVER_ENDPOINT=https://app.testfairy.com

if [ $# -ne 1 ]; then
echo "Usage: testfairy-upload.sh APK_FILENAME"
echo
exit 1
fi

APK_FILENAME=$1
if [ ! -f "${APK_FILENAME}" ]; then
echo "Usage: testfairy-upload.sh APK_FILENAME"
echo
echo "Can't find file: ${APK_FILENAME}"
exit 2
fi

TMP_FILENAME=/tmp/.testfairy.upload.apk
ZIPALIGNED_FILENAME=/tmp/.testfairy.zipalign.apk

if [ -z "${API_KEY}" ]; then
echo "Usage: testfairy-upload.sh APK_FILENAME"
echo
echo "Please update API_KEY with your private API key, as noted in the Settings page"
exit 1
fi

/bin/echo -n "Uploading ${APK_FILENAME} to TestFairy.. "
JSON=`${CURL} -3s ${SERVER_ENDPOINT}/api/upload -F api_key=${API_KEY} -F apk_file=@${APK_FILENAME} -F testers_groups=${TESTER_GROUPS}`

URL=`echo ${JSON} | grep -o '"instrumented_url"\s*:\s*"[^"]*"' | sed 's/"instrumented_url"\s*:\s*"\([^"]*\)"/\1/' | sed 's/\\\//g'`
if [ -z "${URL}" ]; then
echo "FAILED!"
echo
echo "Upload failed, please check your settings"
exit 1
fi

echo "OK!"
/bin/echo -n "Downloading instrumented APK from ${URL}.. "
${CURL} -o ${TMP_FILENAME} -3s ${URL}
echo "OK!"

/bin/echo -n "Re-signing APK file.. "
${ZIP} -qd ${TMP_FILENAME} META-INF/\*
${JARSIGNER} -keystore ${KEYSTORE} -storepass ${STOREPASS} ${TMP_FILENAME} ${ALIAS}
${JARSIGNER} -verify ${TMP_FILENAME} >/dev/null
if [ $? -ne 0 ]; then
echo "FAILED!"
echo
echo "Jarsigner failed to verify, please check parameters and try again"
exit 1
fi

${ZIPALIGN} -f 4 ${TMP_FILENAME} ${ZIPALIGNED_FILENAME}
rm -f ${TMP_FILENAME}
echo "OK!"

/bin/echo -n "Uploading signed APK to TestFairy.. "
${CURL} -3s ${SERVER_ENDPOINT}/api/upload-signed -F api_key=${API_KEY} -F apk_file=@${ZIPALIGNED_FILENAME}
echo "OK!"

rm -f ${ZIPALIGNED_FILENAME}