#对象拷贝转换工具

## 功能说明

此工具可用于对象拷贝转换，对实现了Serializable接口的对象进行深拷贝，未实现Serializable接口的进行浅拷贝。

## 主要类说明

### IgnoreFields

用于类上，声明拷贝时需要忽略的字段名，参数为字符串数组，例：

```
@IgnoreFields({"d","a"})
```

### TransTarget

用于字段上，若目标字段与源字段的字段名不一致，就需要该注解来证明目标字段的字段名。例：

```
@TransTarget("targetTemp")
```

### IgnoreClass

用于字段上，声明拷贝时该字段不需要拷贝到指定的目标类中。例：

```
@IgnoreClass(Target.class)
```

### ObjectTransUtil

工具类，复制转换时调用其中的beanTrans方法。

## 使用范例

```
public class Test {

    public static void main(String[] args) throws InvocationTargetException, NoSuchFieldException, IllegalAccessException {
        Temp temp = new Temp();
        temp.setS("testTemp");
        TempSerializable tempSerializable = new TempSerializable();
        tempSerializable.setS("testTempSerializable");
        Source source = new Source();
        source.setA("a");
        source.setB(1);
        source.setSourceTemp(temp);
        source.setSourceTempSerializable(tempSerializable);
        String[] test1s = {"d", "d"};
        String[] test2s = {"e", "e"};
        String[] test3s = {"f", "f"};
        source.setD(test1s);
        source.setSourceE(test2s);
        source.setSourceF(test3s);
        source.setX(1);
        Target target = ObjectTransUtil.transBean(source, Target.class);
        Target2 target2 = ObjectTransUtil.transBean(source, Target2.class);


        Temp targetTemp = target.getTargetTemp();
        targetTemp.setS("12345");
        TempSerializable targetTempSerializable = target.getTargetTempSerializable();
        targetTempSerializable.setS("12345");

        System.out.println(source);
        System.out.println(target);
        System.out.println(target2);
    }

}

@Data
@IgnoreFields({"d","a"})
class Source {
    String a;
    @IgnoreClass(Target.class)
    Integer b;
    @TransTarget("targetTemp")
    Temp sourceTemp;
    @TransTarget("targetTempSerializable")
    TempSerializable sourceTempSerializable;
    String[] d;
    @TransTarget("targetE")
    String[] sourceE;
    String[] sourceF;
    int x;
}

@Data
class Target {
    String a;
    Integer b;
    Temp targetTemp;
    TempSerializable targetTempSerializable;
    String[] d;
    String[] targetE;
    int x;
}

@Data
class Target2 {
    String a;
    Integer b;
    Temp targetTemp;
    TempSerializable targetTempSerializable;
    String[] d;
    String[] targetE;
    int x;
}

@Data
class Temp {
    String s;
}

@Data
class TempSerializable implements Serializable {
    String s;
}
```