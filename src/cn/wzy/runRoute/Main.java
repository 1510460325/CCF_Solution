package cn.wzy.runRoute;

import java.util.*;

/**
 * Create by Wzy
 * on 2018/7/18 22:44
 * 不短不长八字刚好
 */
public class Main {//行车路线 30
    static class Node{
        int type;
        int end;
        int len;
    }
    static int n;
    static Map<Integer,List<Node>> map = new HashMap<>();
    static int minCost = 9999999;
    static boolean[] visit;
    static void addToMap(int s, int e, int len, int type) {
        List<Node> list = map.get(s);
        if (list == null) {
            list = new ArrayList<>();
            map.put(s, list);
        }
        Node node = new Node();
        node.end = e;
        node.len = len;
        node.type = type;
        list.add(node);
    }

    static void dfs(int now, int cost, int contain) {
        if (cost + contain*contain > minCost) {
            return;
        }
        if (now == n) {
            minCost = cost + contain*contain ;
            return;
        }
        visit[now] =true;
        List<Node> list = map.get(now);
        if (list != null) {
            for (Node node:list) {
                if (visit[node.end])
                    continue;
                if (node.type == 1) {//小路
                    dfs(node.end,cost,contain + node.len);
                }
                else
                    dfs(node.end,cost + contain*contain + node.len,0);
            }
        }
        visit[now] = false;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        visit = new boolean[n + 1];
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int type, s, e, len;
            type = scanner.nextInt();
            s =  scanner.nextInt();
            e =  scanner.nextInt();
            len =  scanner.nextInt();
            addToMap(s, e, len,type);
            addToMap(e,s,len,type);
        }
        dfs(1, 0, 0);
        System.out.println(minCost);
    }

}
/**C语言 40
 #include<iostream>
 using namespace std;

 struct node{
 int end, type, len;
 node* next;
 };

 node* map[502];
 int n, m;
 int visit[502], minCost = 65535;
 void init() {
 for (int i = 0; i <= n; i++) {
 map[i] = new node;
 map[i]->end = i;
 map[i]->next = NULL;
 visit[i] = 0;
 }
 }
 void addToMap(int type, int s, int e, int len) {
 node *p = map[s];
 while (p->next!=NULL) {
 p = p->next;
 }
 node *q = new node;
 q->end = e;
 q->type = type;
 q->len = len;
 q->next = NULL;
 p->next = q;
 }

 void dfs(int now, int cost, int contain) {
 if (cost + contain * contain > minCost)
 return;
 if (now == n) {
 minCost = cost + contain * contain;
 return;
 }
 visit[now] = 1;
 node *p = map[now];
 while (p != NULL) {
 if (visit[p->end]);
 else if (p->type)
 dfs(p->end,cost, contain + p->len);
 else
 dfs(p->end,cost + contain * contain + p->len, 0);
 p = p->next;
 }
 visit[now] = 0;
 }
 void print() {
 for (int i = 0; i <= n; i++) {
 node *p = map[i];
 while (p != NULL) {
 cout << p->end;
 p = p->next;
 }
 cout << endl;
 }
 }
 int main()
 {
 cin >> n >> m;
 init();
 for (int i = 0; i < m; i++) {
 int type, s, e, len;
 cin >> type >> s >> e >> len;
 addToMap(type, s, e, len);
 addToMap(type, e, s, len);
 }
 //print();
 dfs(1, 0, 0);
 cout << minCost << endl;
 return 0;
 }
 */