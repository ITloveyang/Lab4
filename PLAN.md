###main(String[] args):主程序入口，接收用户输文件生成图并允许后项操作。

**Class Graph{**

	node { name;color; Vector edge{to,weight,color;} }
	
	Map <name,node>
	
	Method addNode(String name);
	
	Method addEdge(String u,String v,int val);
	
	Method clear();
	
	Method setNodeColor(String name,String color);
	
	Method setEdgeColor(String u,String v,String color);
	
	Method clearColor();
	
	Method getToList(String name);
	
	Method getEdgeWeight(String u,String v);
	
**}**
---

###Graph createDirectedGraph(String filename):生成有向图

* 读入文本保存为 `String text`
* 预处理文本为单词序列 `text2wordlist`
* 转换单词为原型 `Fun word2origin`
* 遍历序列建立图 `Graph G`
---

###void showDirectedGraph(Graph G)

* 扫描G制作dot文件 `graph.gv`
* 调用Graphviz生成图片 `img.png`
* 直接打开图片或者嵌入
---

###String queryBridgeWords(Graph G, String word1,String word2):查询桥接词

* 两层dfs
* 也许可以在图上显示
---

###String generateNewText(Graph G,String inputText):根据桥接词生成新文本

* 遍历文本搞一下
---

###String calcShortestPath(Graph G, String word1, String word2):计算两个单词最短路径

* 最短路搞一下, Dijkstra什么的
---

###String randomWalk(Graph G):随机游走
---
