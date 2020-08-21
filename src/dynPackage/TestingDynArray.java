package dynPackage;

public class TestingDynArray<E> {
    public static void main(String[] args) {

//        DinaArrayImpl dinaArray = new DinaArrayImpl(20);
//
//        for (int i = 0;i < 10;i++){
//            dinaArray.add(i);
//        }
//        dinaArray.remove(2);
//        //dinaArray.add(2,new Random().nextInt());
//        dinaArray.remove(2);
//        dinaArray.print();
//
//        System.out.println(dinaArray.indexOf(4));
//        System.out.println(dinaArray.lastIndexOf(9));
//        System.out.println(dinaArray.get(2));

        TestingDynArray testingDynArray = new TestingDynArray<>();
        testingDynArray.addTest();
        testingDynArray.addByCountTest();
        testingDynArray.addAllTest();
        testingDynArray.addAllByPositionTest();
        testingDynArray.getByIndexTest();
        testingDynArray.removeTest();
        testingDynArray.indexOfTest();
        testingDynArray.lastIndexOfTest();
        testingDynArray.setTest(2,1);
        testingDynArray.sizeTest();
        testingDynArray.isEmptyTest();
        testingDynArray.containsTest();

    }

    DynArrayImpl<Integer> dynArray = new DynArrayImpl<>();

    void sizeTest() {
        int size = dynArray.size();
        System.out.println("size is " + size);
    }

    void isEmptyTest() {
        boolean isEmpty = dynArray.contains(3);
        System.out.println("element is empty:" + isEmpty);
    }

    void containsTest() {
        boolean containsElement = dynArray.contains(2);
        System.out.println("element is contains:" + containsElement);
    }

    void getByIndexTest() {
        try {
            for (int i = 0; i < dynArray.size(); i++) {
                System.out.println("get elements:" + dynArray.get(i));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("index is upper bounded");
        }
    }

    void setTest(int index, Integer e) throws ArrayIndexOutOfBoundsException {
        System.out.println("set:" + dynArray.set(index, e));
    }

    void addTest() {
        dynArray.add(1);
        dynArray.add(2);
        dynArray.add(3);
        dynArray.add(5);
        dynArray.add(4);
        dynArray.add(4);
        dynArray.add(4);
    }

    void addAllTest() throws ArrayIndexOutOfBoundsException {
        Integer[] e = {7, 2, 4, 5, 7};

        dynArray.addAll(e);
    }

    void addByCountTest() {
        Integer[] el = {4, 7, 8, 9};
        dynArray.addByCount(el, 0, 4);
    }

    void addAllByPositionTest() {
        Integer[] el = {5, 7, 9, 8};
        dynArray.addAllByPosition(1, el);
    }

    void removeTest() {
        int removed = (int) dynArray.remove(4);
        System.out.println("removed :" + removed);
    }

    void indexOfTest() {
        int index = dynArray.indexOf(2);
        System.out.println("index is:" + index);
    }

    void lastIndexOfTest() {
        int lastIndex = dynArray.lastIndexOf(8);
        System.out.println("last index is:" + lastIndex);
    }


}
