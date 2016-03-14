#include <fstream>
#include <iostream>
#include <ostream>
#include <time.h>
#include <cstdlib>

using namespace std;


int main(){


	ofstream myFile;
	myFile.open("randoms.txt");
	
	srand(time(NULL));

	for(int i = 0; i < 1000000; i++){
			myFile << rand() << endl;
	}
	myFile.close();



}
