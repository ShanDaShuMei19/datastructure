#include<iostream>
#include<stdio.h>
#include<fstream>
#include<string>
#include<cstring>

#define MVNum 26

using namespace std;

//一个子节点

typedef struct

{

int weight;

char data;

int parent;

int lchild;

int rchlid;

}HTNode, * HuffTree;

typedef struct

{

char data;

char * code;

}HuffmanCode[MVNum + 1];

void swap(int & s1, int & s2)

{

	int temp;

	temp = s1;

	s1 = s2;

	s2 = temp;

}

int Select(HuffTree HT, int n, int & s1, int & s2)

{

	int min1 = INT_MAX, min2 = INT_MAX;

	for (int i = 1; i <= n; i++)

	{//min1>=min2;

		if (HT[i].parent == 0)

			if (HT[i].weight < min1)

			{

				min1 = HT[i].weight;

				s1 = i;

				if (min1 < min2)

				{//保持min1>=min2

					swap(min1, min2);

					swap(s1, s2);

				}



			}



	}

	return 1;

}

int Initialization(HuffTree & HT, int n)

{

	ofstream fout("f:\\TEMPASD\\hfmTree.dat", ios::out | ios::binary);

	if (n <= 1)return 0;

	int m = 2 * n - 1;

	HT = new HTNode[m + 1];

	for (int i = 0; i <= m; i++)

	{

		HT[i].data = '#';

		HT[i].parent = 0;

		HT[i].lchild = 0;

		HT[i].rchlid = 0;

	}

	cout << "input data&weight" << endl;

	for (int i = 1; i <= n; i++)

	{

		cin >> HT[i].data >> HT[i].weight;

	}

	/*---------创建哈夫曼树-------*/

	int s1, s2;

	for (int i = n + 1; i <= m; i++)

	{

		Select(HT, i - 1, s1, s2);

		HT[s1].parent = i; HT[s2].parent = i;

		HT[i].lchild = s1; HT[i].rchlid = s2;

		HT[i].weight = HT[s1].weight + HT[s2].weight;

	}

	fout.seekp(0, ios::beg);

	for (int i = 0; i <= m; i++)

	{

		fout.write(reinterpret_cast<char *>(&(HT[i])), sizeof(HTNode));

	}

	fout.close();

	return 1;

}
//初始化
int Initialization_1(HuffTree & HT, int n)

{

	ifstream fin("f:\\TEMPASD\\hfmTree.dat", ios::in | ios::binary);

	if (n <= 1)return 0;

	int m = 2 * n - 1;

	HT = new HTNode[m + 1];

	fin.seekg(0, ios::beg);

	if (fin)

	{

		HTNode temp;

		for (int i = 0; i <= m; i++)

		{

			fin.read(reinterpret_cast<char *>(&(temp)), sizeof(HTNode));

			HT[i] = temp;

		}



	}

	else

	{

		cout << "File could not be open" << endl;

		return 0;

	}

	return 1;

}

void CreateHuffmanCode(HuffTree & HT, HuffmanCode & HC, int n)

{

	char * cd = new char[n];

	if (HT[2 * MVNum - 1].lchild == 0 || HT[2 * MVNum - 1].rchlid == 0)

	{//内存中没有树，需重新添加

		Initialization_1(HT, n);

	}

	cd[n - 1] = '\0';

	for (int i = 1; i <= n; i++)

	{

		int start = n - 1;

		int c = i, f = HT[i].parent;

		HC[i].data = HT[i].data;

		while (f != 0)

		{

			start--;

			if (HT[f].lchild == c)

				cd[start] = '0';

			else

				cd[start] = '1';

			c = f; f = HT[f].parent;

		}

		HC[i].code = new char[n - start];

		strcpy_s(HC[i].code, n - start, &cd[start]);

	}

	delete cd;

}

int Locate(HuffmanCode HC, char ch)

{

	for (int i = 0; i <= MVNum; i++)

	{

		if (ch == HC[i].data)

			return i;

	}

	return  - 1;

}

void Encoding(HuffmanCode HC)

{

	char c; int i;

	ifstream fin; fin.open("f:\\TEMPASD\\ToBeTran.txt", ios::in);

	ofstream fout; fout.open("f:\\TEMPASD\\CodeFile.txt", ios::out);

	if (!fin)

	{

		cerr << "File could not be open!" << endl;

		abort();

	}

	while (fin.get(c))

	{

		if (c < 'A' || c>'Z')

			continue;

		i = Locate(HC, c);

		fout << HC[i].code;

	}

	fin.close();

	fout.close();

}

void Decoding(HuffTree HT)

{

	char c; int i = 2 * MVNum - 1;

	ofstream fout("f:\\TEMPASD\\TextFile.txt", ios::out);

	ifstream fin("f:\\TEMPASD\\CodeFile.txt", ios::in);

	if (!fin)

	{

		cerr << "File could not be open!" << endl;

		abort();

	}

	while (fin.get(c))

	{

		if (c == '0')

			i = HT[i].lchild;

		else

			i = HT[i].rchlid;

		if (HT[i].lchild == 0 && HT[i].rchlid == 0)

		{

			fout << HT[i].data;

			i = 2 * MVNum - 1;

		}

	}

	fout << '\n';

	fout.close();

	fin.close();

}

void Print(HuffmanCode HC)

{

	char c; int flag = 1;

	ifstream fin("f:\\TEMPASD\\CodeFile.txt", ios::in);

	ofstream fout("f:\\TEMPASD\\CodePrin.txt", ios::out);

	while (fin.get(c))

	{

		cout << c; fout << c; flag++;

		if (flag == 50)

		{

			cout << endl;

			fout << '\n';

			flag = 1;

		}

	}



}

void printhelp(HuffTree & HT, string ss, int num)

{

	if (HT[num].lchild <= 0 && HT[num].rchlid <= 0)

	{

		ss += "   ";

		cout << ss << HT[num].data << endl;

		return;

	}

	ss += "   ";

	printhelp(HT, ss, HT[num].rchlid);

	cout << ss << HT[num].data << endl;

	printhelp(HT, ss, HT[num].lchild);

}

void Tree_printing(HuffTree & HT)

{

	if (HT[2 * MVNum - 1].lchild == 0 || HT[2 * MVNum - 1].rchlid == 0)

		Initialization_1(HT, MVNum);

	string ss = "";

	printhelp(HT, ss, 2 * MVNum - 1);

}



//源.cpp

#include"Huff.h"

int main()

{

	HuffTree HT = new HTNode[2 * MVNum]; 
	int flag = 1; char ch;

	HuffmanCode HC;

	for (int i = 0; i <= 2 * MVNum - 1; i++)

	{

		HT[i].data = '#';

		HT[i].parent = 0;

		HT[i].lchild = 0;

		HT[i].rchlid = 0;

	}

	while (1)

	{

		cout << "-----------哈夫曼编/译码器-----------" << endl;

		cout << "-----------I.初始化------------------" << endl;

		cout << "-----------E.编码--------------------" << endl;

		cout << "-----------D.译码--------------------" << endl;

		cout << "-----------P.印代码文件--------------" << endl;

		cout << "-----------T.印哈夫曼树--------------" << endl;

		cout << "-----------Q.退出程序----------------" << endl;

		cin >> ch;

		switch (ch)

		{

			case 'I':Initialization(HT, MVNum); break;

			case 'E':CreateHuffmanCode(HT, HC, MVNum); Encoding(HC); break;

			case 'D':Decoding(HT); break;

			case 'P':Print(HC); break;

			case 'T':Tree_printing(HT); break;

			case 'Q':flag = 0; break;

		default:break;

		}

		if (flag == 0)

			break;

	}

	return 0;

}
