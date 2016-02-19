#include <fstream>
#include <iostream>
#include <ostream>

using namespace std;


int main(){


	ofstream myFile;
	myFile.open("randoms.txt");
	for(int j = 0; j < 1000; j++){

		for(int i = 0; i < 100; i++)
		{
			myFile << i << endl;
		}


	}
	myFile.close();



}
