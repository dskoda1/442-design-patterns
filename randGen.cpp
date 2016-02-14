#include <fstream>
#include <iostream>
#include <ostream>

using namespace std;


int main(){


	ofstream myFile;
	myFile.open("randoms.txt");
	for(int i = 0; i < 1000000; i++)
	{
		myFile << i << endl;
	}
	myFile.close();



}
