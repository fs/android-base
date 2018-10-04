# Android Java Code Style

## Основные положения

Основная задача поддержки стили кода заключается в улучшение его читабельности, а так же в упрощении навигации по ниму.
Не стоит забывать, что не только вы являетесь читателями вашего кода, а есть еще ваши коллеги, которые вас ревьювят.

Общее правило: Код должен соответствовать [*Code Conventions for the Java TM Programming Language*][oracle_conv].
Если информация этого документа конфликтует с
информацией из приведенных ранее, то следует придерживаться этого документа.

Перед *Pull Request* не забывай делать форматирование с помощью горящих клавиш `Ctrl+Alt+L ` or `Cmd+Alt+L`.

## Android xml style

### Зачем это нужно

- Простой поиск любого ресурса
- Логические, предсказуемые имена
- Упорядоченные ресурсы
- Типизированные ресурсы

#### Имена layout

Имена xml layouts должны соответствовать шаблону prefix_semantic, где prefix:

- activity_ - макет активити (activity_main)

- fragment_ - макет фрагмента (fragment_account)

- dialog_ - макет диалога (dialog_verify_code)

- layout_ - некий layout, встраиваемый в другой layout (*include*) (layout_collapsing_bar)

- item_ - элемент списка (item_book)

- view_ - макет для кастомного android.View (view_timepicker)

### Id компонентов в layout-е

id компонентов в layout должны соответствовать шаблону *postfix_semantic*, где *postfix* тип компонента, 
в сокращенном ввиде из заглавных букв (пакет саппорта в учет не входит). Пример, самых распространенных элементов:

- tv_ - TextView
- iv_ - ImageView
- btn_ - Button
- ibtn_ - ImageButton
- et_ - EditText
- ti_ - TextInputLayout
- pb_ - ProgressBar
- chb_ - CheckBox
- rv_ - RecyclerView
- containter_ - любой ViewGroup, выступающий ввиде родителя-контейнера
- view_ - кастомная вью
- divider_ - view-разделитель
- cv_ - CardView

### Порядок атрибутов в layout-е

- View Id
- Style
- Layout width and layout height
- Other layout attributes, sorted alphabetically
- Remaining attributes, sorted alphabetically
- Support and custom attributes , sorted alphabetically

### Имена констант в xml:

- postfix_screen_semantic_size, где:
    - screen - имя экрана, где используется константа, может отсутствовать, если используется на многих экранах
    - semantic - смысл
	- size - размер ресурса, может отсутствовать(small, medium, large, xlarge or Xdp)
    - postfix - назначение:
        - text_ строковая константа используется в TextView,
        - btn_ строковая константа используется в Button
        - message_, error_message_ - используется в Toast / Snack
        - hint_ - подсказка в EditText
        - padding_, margin_, elevation_ - в dp
        - width_, height_, size_ - в dp (size if width == height)
		- textsize_ - размер текста в sp

## Java style
		
### Структура класса 

- Constants
- Fields
- Constructors
- Methods
- Inner classes or interfaces

### Расоложение методов 

#### Вариант 1, на уровнях абстракции:

Берем метод самого высокого уровня абстракции. После него располагаются
методы которые он вызывает, после этих методов, методы которые вызываются в них,
и так пока не будет достигнут самый нижний уровень абстракции. После этого
берется следующий метод из верхнего, неупорядоченного, уровня абстракции, и повторяем шаги.

Его несколько тяжелее поддерживать, но это позволяет легче читать код класса
сверху вниз, не прибегая к переходам.

#### Вариант 2:

- abstract methods
- override methods
- public methods
- protected methods
- private methods
	
### Именование и порядок переменных

- static, non-static 
- public, protected, private

- fields start with a lower case letter
- constants are ALL_CAPS_WITH_UNDERSCORES

Пример: 
```
public static final int SOME_CONSTANT = 42;
private static MyClass sSingleton; 	
	
public int publicField; 
int packagePrivate; 
protected int protected;
private int private; 
```

### Комментарии

- Комментарии пишутся на **английском языке**.

- Методы **необходимо** дополнять комментариями, если их название не полностью
объясняет выполняемые ими действия.

- Комментарии для методов и классов должны быть в формате javadoc.

- Комментарии необходимы для неочевидных участков кода.

- Комментарии для классов и методов не должны раскрывать деталей реализации.


### Константы-ключи

- EXTRA_ - intent extra

- PREF_ - shared preferences

- ARG_ - fragment argument in bundle

- ACTION_ - intent action

### Имена классов
- для парсинга ответа должны быть с суффиксом Response

- для формирования запроса должны быть с суффиксом Request

### Имена view-переменных

Именование переменных, соответствующих компонентам, должно соответствовать
шаблону `postfixSemantic`, где `postfix`:

- tv - TextView
- btn - Button
- ibtn - ImageButton
- iv - ImageView
- pb - ProgressBar
- et - EditText
- ti - TextInputLayout
- rb - RadioButton
- chb - CheckBox
- rv - RecyclerView
- cv - CardView

### Использование аннотаций

Аннотации идут друг под другом и располагаются над целью. Для аннотации `@Override` допустимо 
написание в одну строку с переопределяемым методом. Пример:
`@Override protected void onCreate(Bundle state) {}`

Если возвращаемое значение метода может быть `null`, необходимо добавить
аннотацию `@Nullable`. То же и для параметров метода.

Если параметры или возвращаемое значение метода являются id ресурса,
то следует пометить их аннотациями `@StringRes`, `@LayoutRes`, `@DrawableRes` и тд.

Все поля в объектах `SomeResponse`, `SomeRequest` или другого Json объекта  должны быть
помечены аннотацией `@SerializedName` или быть внесены в исключение в настройках ProGuard-а

### Остальное

- Цепочечные вызовы следует располагать друг под другом на новой строке. 
Пример: 
```
Picasso.with(context)
	.load("https://www.a90skid.com/.../800x800.jpg")
	.error(R.drawable.ic_error)
	.transform(PaletteTransformation.instance())
	.into(imageView);
```

- большинство методов, возвращающих boolean должны начинаться с `is***`, `are***`.
Допустимо также использовать префиксы `can***`, `should***`, `has***`, `not***`.
**Примечание:** соблюдаем грамматику английского языка, не будь как я!

[oracle_conv]: http://www.oracle.com/technetwork/java/codeconvtoc-136057.html