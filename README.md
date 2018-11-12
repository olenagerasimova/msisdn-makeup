# phmakeup


Library to work with phone numbers. It is capable of cleaning and formatting msisdns.

Phones are formatted according to given profile. In mobicont terms profiles are "Профили номерной емкости", they basically describe number's length and prefix (country code).  
Profile description format:
```
length,country_code;length,country_code;...
```
Country code can be skipped, pair of length and prefix is called msisdn/phone format. Example:
```
11,7;11;12,380;12,375;12;13
```
There is special profile `Profile.UNLIMITED` in the library, that allows msisdns up to 15 signs with any prefixes. This profile correspond to mobicont `ValidMSISDNFormat.NO_CHECK_FORMAT_ID` constant.

## How to use
 To makeup phone, create `PhoneFor` instance and call `makeup` method: 
 ```java
String cleanPhone = new PhoneFor("(909) 695-952-12", new ProfileFor("13,71;13;10,8")).makeup();
```
`cleanPhone` will be equal to `7190969595212`, as given phone is suitable for `13,71`. If given phone does not suit any of the given profile, `IlligalArgumentException` is thrown.

Please, note, 
* that msisdn will be formatted according first suitable format in given profile. 
* Russian profile is `7,11`, but phone numbers that starts with 8 are also suitable for it. In the example below result will be `89096959512` in both cases
```java
String ruPhone2 = new PhoneFor("8 (909) 695-95-12", Profile.RU).makeup();
String ruPhone1 = new PhoneFor("8 (909) 695-95-12", new ProfileFor("11,7;10,5")).makeup();
```
* Azerbaijan profile is `12,994`, but phones with `zero` prefix are also formatted as Az:
```java
String azPhone = new PhoneFor("0 854 741 256", new ProfileFor("12,994")).makeup(); // Result is 994854741256
```

## How to make changes
Create new branch, make changes, send a merge request. Someone will review
your changes and apply them to the `master` branch shortly, provided
they don't violate quality standards. To avoid frustration, before
sending us your merge request please run full Maven build:
```
$ mvn clean install -Pqulice
```
Note that 
* this small library is created according to EO standards, if you do not know what it is, please, visit https://www.elegantobjects.org/
* `qulice` https://www.qulice.com/ is used to check code style, do not dare to disable it, write clean code instead

### Unit-tests
Unit test is junit4 test case started by `maven-surefire-plugin`.
Test method name should be read as an English sentence: `TargetClass` can `methodName`
as described in "Test Method Names" section in this post: https://www.yegor256.com/2014/04/27/typical-mistakes-in-java-code.html

For assertions "Hamcrest" library is used, so it's good practice to design each test method
as single assertion with matching expected result.
```java
@Test
public void returnZero() {
  MatcherAssert.assertThat(
    new TargetClass().action(),
    new IsEqual<>(0)
  );
}
```

If all test methods are named properly javadocs for them can be skipped with:
```java
@checkstyle JavadocMethodCheck (500 lines)
```   