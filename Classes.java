public class Classes {
    private static int x;

    public class InnerClass{
        public void setX(){
            x = 32;
            System.out.println(x);
        }
    }
    public void createInnerClassObject(){
        InnerClass myInnerClass = new InnerClass();
        myInnerClass.setX();

    }
    public static void main(String[] args){
        Classes classes = new Classes();
        InnerClass innerClass = classes.new InnerClass();
//        classes.createInnerClassObject();
        innerClass.setX();
        System.out.println(x);

    }

}
