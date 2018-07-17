import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("欢迎进入<辉哥最帅彩票系统>, 摸摸大!");

        // 公共资源
        // 1.输入工具
        Scanner scanner = new Scanner(System.in);
        // 2.记录账号和密码
        // 结构: [账号, 密码]
        String[] acount = new String[2];
        //String[][] acounts = new String[10][2];
        // 3.随机数工具
        Random random = new Random();
        // 4.记录所选号码
        int[][] numbers = new int[5][7];
        // 5.记录所购买的注数
        int count = 0;
        // 6.是否登录
        boolean isLogin = false;

        boolean isLoop = true;
        while (isLoop) {
            System.out.println("---请选择---");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("3.机选");
            System.out.println("4.自选");
            System.out.println("5.查看");
            System.out.println("6.开奖");
            System.out.println("0.退出");
            System.out.println("----------");

            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    // 登录
                    System.out.println("输入用户名:");
                    String username = scanner.next();
                    System.out.println("输入密码:");
                    String password = scanner.next();
                    if (username.equals(acount[0]) && password.equals(acount[1])) {
                        System.out.println("登录成功!😆");
                        isLogin = true;
                    } else {
                        System.out.println("登录失败!用户名或密码错误!");
                    }
                    break;
                case 2:
                    // 判断是否已经注册
                    if (acount[0] != null) {
                        System.out.println("已注册, 请登录");
                        break;
                    }
                    // 注册
                    System.out.println("请输入用户名:");
                    String registerName = scanner.next();
                    acount[0] = registerName;
                    while (true) {
                        System.out.println("请输入密码:");
                        String registerPassword = scanner.next();
                        System.out.println("再次输入密码:");
                        String repeatPassword = scanner.next();
                        if (registerPassword.equals(repeatPassword)) {
                            acount[1] = registerPassword;
                            break;
                        } else {
                            System.out.println("两次输入的密码不一致!😈");
                        }
                    }
                    System.out.println("注册成功!😘");
                    break;
                case 3:
                    if (!isLogin) {
                        System.out.println("未登录!");
                        break;
                    }

                    // 判断购买的注数是否超过最大限额
                    if (count == 5) {
                        System.out.println("已达到购买上限!");
                        break;
                    }

                    // 机选
                    int[] item = new int[7];
                    // 红球, 选6个, 范围[1, 33], 不能重复
                    // 蓝球, 选1个, 范围[1, 16]

                    // 随机红球
                    for (int i = 0; i < 6; i++) {
                        int temp = random.nextInt(33) + 1;

                        // 判断和之前的号码是否重复
                        boolean isRepeat = false;
                        for (int j = 0; j < i; j++) {
                            if (temp == item[j]) {
                                isRepeat = true;
                                break;
                            }
                        }

                        if (!isRepeat) {
                            item[i] = temp;
                        } else {
                            i--;
                        }
                    }

                    // 对红球进行排序, 从小到大
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5 - i; j++) {
                            if (item[j] > item[j + 1]) {
                                int temp = item[j];
                                item[j] = item[j + 1];
                                item[j + 1] = temp;
                            }
                        }
                    }

                    // 随机蓝球
                    item[6] = random.nextInt(16) + 1;

                    // 打印号码
                    for (int i = 0; i < item.length; i++) {
                        System.out.print(item[i] + " ");
                    }
                    System.out.println();

                    // 存入数组
                    numbers[count] = item;

                    // 购买的注数加1
                    count++;

                    break;
                case 4:
                    if (!isLogin) {
                        System.out.println("未登录!");
                        break;
                    }

                    if (count == 5) {
                        System.out.println("已到达购买上限");
                        break;
                    }

                    // 自选
                    int[] self = new int[7];

                    for (int i = 0; i < 6; i++) {
                        System.out.println("输入红球号码(1-33):");
                        int number = scanner.nextInt();

                        if (number < 1 || number > 33) {
                            System.out.println("输入有误");
                            i--;
                            continue;
                        }

                        // 是否重复
                        boolean isSame = false;
                        for (int j = 0; j < i; j++) {
                            if (number == self[j]) {
                                isSame = true;
                                break;
                            }
                        }

                        if (isSame) {
                            System.out.println("输入号码重复!");
                            i--;
                        } else {
                            self[i] = number;
                        }
                    }

                    while (true) {
                        System.out.println("请输入蓝球号码(1-16):");
                        int num = scanner.nextInt();
                        if (num < 1 || num > 16) {
                            System.out.println("输入有误!");
                        } else {
                            self[6] = num;
                            break;
                        }
                    }

                    // 红球排序
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5 - i; j++) {
                            if (self[j] > self[j + 1]) {
                                int temp = self[j];
                                self[j] = self[j + 1];
                                self[j + 1] = temp;
                            }
                        }
                    }

                    System.out.println("所选号码:");
                    for (int i = 0; i < 7; i++) {
                        System.out.print(self[i] + " ");
                    }
                    System.out.println();

                    numbers[count] = self;

                    count++;

                    break;
                case 5:
                    if (!isLogin) {
                        System.out.println("未登录!");
                        break;
                    }

                    // 查看购买的彩票
                    System.out.println("购买的彩票:");
                    for (int i = 0; i < count; i++) {
                        for (int j = 0; j < 7; j++) {
                            // %02d: 不足2位, 补足
                            System.out.printf("%02d ", numbers[i][j]);
                        }
                        System.out.println();
                    }
                    break;
                case 6:
                    // 随机产生1注号码(中奖号码)
                    int[] prize = new int[7];
                    // 红球, 选6个, 范围[1, 33], 不能重复
                    // 蓝球, 选1个, 范围[1, 16]

                    // 随机红球
                    for (int i = 0; i < 6; i++) {
                        int temp = random.nextInt(33) + 1;

                        // 判断和之前的号码是否重复
                        boolean isRepeat = false;
                        for (int j = 0; j < i; j++) {
                            if (temp == prize[j]) {
                                isRepeat = true;
                                break;
                            }
                        }

                        if (!isRepeat) {
                            prize[i] = temp;
                        } else {
                            i--;
                        }
                    }

                    // 对红球进行排序, 从小到大
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5 - i; j++) {
                            if (prize[j] > prize[j + 1]) {
                                int temp = prize[j];
                                prize[j] = prize[j + 1];
                                prize[j + 1] = temp;
                            }
                        }
                    }

                    // 随机蓝球
                    prize[6] = random.nextInt(16) + 1;

                    // 打印号码
                    System.out.println("开奖号码:");
                    for (int i = 0; i < prize.length; i++) {
                        System.out.print(prize[i] + " ");
                    }
                    System.out.println();

                    // 判断中奖

                    // 遍历购买的彩票
                    for (int i = 0; i < count; i++) {
                        int[] n = numbers[i];

                        // 计算红球数量
                        int red = 0;
                        for (int j = 0; j < 6; j++) {
                            for (int k = 0; k < 6; k++) {
                                if (n[j] == prize[k]) {
                                    red++;
                                    break;
                                }
                            }
                        }

                        // 计算蓝球数量
                        int blue = 0;
                        if (n[6] == prize[6]) {
                            blue++;
                        }

                        if (red == 6 && blue == 1) {
                            System.out.println("一等奖");
                        } else if (red == 6 && blue == 0) {
                            System.out.println("二等奖");
                        } else if (red == 5 && blue == 1) {
                            System.out.println("三等奖");
                        } else if (red == 5 && blue == 0) {
                            System.out.println("四等奖");
                        } else if (red == 4 && blue == 1) {
                            System.out.println("四等奖");
                        } else if (red == 4 && blue == 0) {
                            System.out.println("五等奖");
                        } else if (red == 3 && blue == 1) {
                            System.out.println("五等奖");
                        } else if (red == 2 && blue == 1) {
                            System.out.println("六等奖");
                        } else if (red == 1 && blue == 1) {
                            System.out.println("六等奖");
                        } else if (red == 0 && blue == 1) {
                            System.out.println("六等奖");
                        } else {
                            System.out.println("未中奖");
                        }
                    }

                    // 清空购买号码
                    numbers = new int[5][7];
                    count = 0;

                    break;
                case 0:
                    isLoop = false;
                    System.out.println("常来呀!");
                    break;
                default:
                    System.out.println("地球不适合你!");
                    break;
            }
        }


    }
}
