import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DishApp {

    static List<Dish> dishlist = new ArrayList<>();
    private static Scanner s;

    static List<Dish> memberDishlist = new ArrayList<>();

    public static void main(String[] args) {
        initDish();

        s = new Scanner(System.in);

        while (true){
            showMenu();

            int Order = s.nextInt();

            choice(Order);
            if (Order == 3){
                break;
            }
        }

    }

    public static void showMenu(){
        System.out.println("-----主菜单-----");
        System.out.println("菜单\t\t\t 1");
        System.out.println("已点菜品\t\t 2");
        System.out.println("买单\t\t\t 3");
        System.out.println("删除菜品\t\t 4");
        System.out.println("-----请选择您需要的服务-----");
    }
    public static void initDish(){
        Dish dish = new Dish(1,"鱼香肉丝",34.0);
        Dish dish2 = new Dish(2,"北京烤鸭",59.0);
        Dish dish3 = new Dish(3,"水煮肉片",49.0);
        Dish dish4 = new Dish(4,"东坡肉",68.0);
        Dish dish5 = new Dish(5,"油焖大虾",78.0);

        dishlist.add(dish);
        dishlist.add(dish2);
        dishlist.add(dish3);
        dishlist.add(dish4);
        dishlist.add(dish5);
    }

    public static void choice(int order){
        switch (order) {
            case 1:
                showDistMenu();
                while(true){
                    int id = s.nextInt();

                    if (id == 0){
                        System.out.println("-----退出点菜操作-----");
                        break;
                    }
                    Dish dish = dishlist.get(id - 1);
                    System.out.println("亲，您点了："+dish.name);
                    memberDishlist.add(dish);
                }
            case 2:
                showMemberDish();
                break;
            case 3:
                CostMoney();
                break;
            case 4:
                System.out.println("-----您目前点的菜有-----");
                for (int i = 0; i < memberDishlist.size(); i++) {
                    Dish dish = memberDishlist.get(i);
                    System.out.println(dish.id+"\t"+dish.name);
                }
                Delete();
                break;
            default:
                System.out.println("无效的选项，请重新选择。");
        }
    }

    private static void Delete() {
        while (true){
            System.out.println("------请输入您要删除的菜品,取消请按0-----");
            int idToDelete = s.nextInt();

            if (idToDelete == 0) {
                System.out.println("-----退出删除操作-----");
                break;
            }

            Iterator<Dish> iterator = memberDishlist.iterator();
            boolean isDeleted = false;
            while (iterator.hasNext()){
                Dish dish = iterator.next();
                if (idToDelete == dish.id){
                    iterator.remove();
                    isDeleted = true;
                    System.out.println("已删除菜品"+ dish.name );
                }
            }
            if (!isDeleted){
                System.out.println("未找到指定ID的菜品。");
            }
        }

    }

    private static void CostMoney() {
        double money = 0;
        for (int i = 0; i < memberDishlist.size(); i++) {
            Dish dish = memberDishlist.get(i);
           money +=  dish.price;
        }

        System.out.println("您需支付"+ money +"元");
    }

    private static void showMemberDish() {
        System.out.println("-----您点的菜品如下-----");
        for (int i = 0; i < memberDishlist.size(); i++) {
            Dish dish = memberDishlist.get(i);
            System.out.println(dish.id+"\t"+dish.name);
        }
    }



    private static void showDistMenu() {
        System.out.println("-----请你点菜-----");
        for (int i = 0; i < dishlist.size(); i++) {
            Dish dish = dishlist.get(i);
            System.out.println(dish.id+"\t"+dish.name+"\t"+dish.price);
        }
        System.out.println("-----输入序号点菜，按0返回上一级-----");
    }
}
