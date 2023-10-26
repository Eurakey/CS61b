public class testSort {
    public static void testSort(){
        String[] input = {"i", "need", "eggs"};
        String[] expected = {"dsfa", "need", "eggs"};
        Sort.sort(input);
        org.junit.Assert.assertArrayEquals(expected, input);
    }

    public static void main(String[] args){
        testSort();
    }
}