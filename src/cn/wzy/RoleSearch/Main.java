package cn.wzy.RoleSearch;

import java.util.*;

public class Main { //权限查询 90
    static Map<String,List<String>> roles = new HashMap<>();
    public static void checkRole(String roleName) {
        int index = roleName.indexOf(':');
        if (index != -1) {//有等级
            roles.put(roleName.substring(0,index),null);//有这个key就代表是等级
        }
    }
    public static void handler(List<String> roles, String str) {
        int index = str.indexOf(':');
        if (index == -1) {//无等级权限
            roles.add(str);
        }
        else {
            String roleName = str.substring(0,index);
            String levelStr = str.substring(index + 1);
            int level = Integer.parseInt(levelStr);
            while (level != -1) {
                roles.add(roleName + ":" + level);
                level--;
            }
        }
    }

    public static void addRole(String person, String roleName) {
        List<String> list = roles.get(roleName);
        List<String> nowRoles = roles.get(person);
        if (nowRoles == null) {
            nowRoles = new ArrayList<>(list);
        }
        else {
            nowRoles.addAll(list);
        }
        roles.put(person, nowRoles);
    }



    public static void getAns(String name, String role) {
        List<String> nowRoles = roles.get(name);//获取角色所有权限
        if (nowRoles == null) {//不存在这个人
            System.out.println(false);
        }
        else {
            int index = role.indexOf(':');
            if (index != -1) {//有“:”准确查询
                System.out.println(nowRoles.contains(role));
            }
            else {//没有等级查询
                if (roles.containsKey(role)) {//该权限带有等级
                    int maxLevel = 0;
                    for (;;maxLevel++) {
                        String maxRole = role + ":" + maxLevel;
                        if (!nowRoles.contains(maxRole)) {
                            break;
                        }
                    }
                    maxLevel--;
                    if (maxLevel == -1) {
                        System.out.println(false);
                    }
                    else {
                        System.out.println(maxLevel);
                    }
                }
                else {//该权限不带等级
                    System.out.println(nowRoles.contains(role));
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n-- != 0) {
            checkRole(scanner.next());
        }
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int m = scanner.nextInt();
            List<String> list = new ArrayList<>();
            for (int j = 0; j < m; ++j) {
                String role = scanner.next();
                handler(list,role);
            }
            roles.put(name,list);
        }

        n = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            String name = scanner.next();
            int m = scanner.nextInt();
            for (int j = 0; j < m; ++j) {
                String roleName = scanner.next();
                addRole(name, roleName);
            }
        }

        n = scanner.nextInt();
        while (n-- != 0) {
            String name = scanner.next();
            String role = scanner.next();
            getAns(name, role);
        }
    }
}
