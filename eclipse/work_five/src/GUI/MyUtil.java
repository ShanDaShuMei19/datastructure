package GUI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MyUtil {
	
	static Queue visited;
	static int[] dis;
	static HashMap path;
	
	public static String myDijkstra1(int len,int[][] matrix,String[] v_str,int v) {
		visited = new LinkedList();
		dis = new int[len];
		path = new HashMap();
		//初始化路径
		for(int i=0;i<v_str.length;i++)
			path.put(i, "");	
		dijkstra(matrix,v_str,v,path);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("路径长度\t路径\n");
		for(int h=0;h<v_str.length;h++)
		{
			stringBuilder.append(dis[h]);
			stringBuilder.append("\t");
			stringBuilder.append(path.get(h).toString());	
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
	
	public static String myDijkstra2(int len,int[][] matrix,String[] v_str,int v,int v2) {
		visited = new LinkedList();
		dis = new int[len];
		path = new HashMap();
		//初始化路径
		for(int i=0;i<v_str.length;i++)
			path.put(i, "");		
		dijkstra(matrix,v_str,v,path);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("路径长度\t路径\n");
		stringBuilder.append(dis[v2]);
		stringBuilder.append("\t");
		stringBuilder.append(path.get(v2).toString());	
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}

	//邻接矩阵  顶点  目的地
	public static void dijkstra(int[][] matrix,String[] v_str,int v,HashMap path)
	{
		//初始化distance
		for(int i=0;i<v_str.length;i++)
		{
			//路径添加起点v
			path.put(i, path.get(i)+""+v_str[v]);
			//v点到v点
			if(i==v)
				dis[i]=0;
			//有通路，设初值
			else if(matrix[v][i]!=-1)
			{
				dis[i]=matrix[v][i];
				path.put(i, path.get(i)+"-->"+v_str[i]);
			}
			//无，设为最大值
			else
				dis[i]=Integer.MAX_VALUE;
		}
		visited.add(v);
		while(visited.size()<v_str.length)
		{
			//当前最短距离k
			int k=-1;
			//设初值为max
			int min_num=Integer.MAX_VALUE;
			for(int i=0;i<dis.length;i++)
			{
				if(!visited.contains(i))
				{
					if(dis[i]<min_num)
					{
						min_num=dis[i];
						k=i;
					}
				}
			}
			//k点已访问
			visited.add(k);
			if(k!=-1)
			{
				for(int j=0;j<v_str.length;j++)
				{
					if(matrix[k][j]!=-1)
					{
						//
						if(dis[j]>dis[k]+matrix[k][j])
						{
							dis[j]=dis[k]+matrix[k][j];
							path.put(j, path.get(k)+"-->"+v_str[j]);					
						}
					}
					
				}//end of for 
			}//end of if
		}//end of while
	}//end of method	
}//end of class