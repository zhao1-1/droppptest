package datastructure;

/**
 * @author bin2.zhao (D52B48 in ZhangMen)
 * @since 2021/9/6 14:22
 */
public class Test5 {
    public static void main(String[] args) {
        Solution5 sl5 = new Solution5();

//        System.out.println(sl5.fib(2));
//        System.out.println(sl5.fib(5));
//        System.out.println(sl5.fib(44));

//        System.out.println(sl5.fib_2(2));
//        System.out.println(sl5.fib_2(5));
//        System.out.println(sl5.fib_2(44));


//        System.out.println(sl5.climbStairs(2));
//        System.out.println(sl5.climbStairs(3));
//        System.out.println(sl5.climbStairs(5));
//        System.out.println(sl5.climbStairs(7));
//        System.out.println(sl5.climbStairs(45));


//        System.out.println(sl5.callCellDivision(6));
//        System.out.println(sl5.callCellDivision(8));
//        System.out.println(sl5.callCellDivision(2));
//        System.out.println(sl5.callCellDivision(50));


        Solution3 sl3 = new Solution3();
        sl5.reversePrint(sl3.buildList(new int[]{1,3,2}));
        sl5.reversePrint(sl3.buildList(new int[]{2}));
        sl5.reversePrint(sl3.buildList(new int[]{1,2,3,4,5,6,7,8,9,10}));

    }
}
