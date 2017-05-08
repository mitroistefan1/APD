#include <iostream>
#include <fstream>
#include <stdlib.h>
#include<time.h>
#define inf 1001

using namespace std;


int main(){

    /*int **dist,n;
    int matrixSize=702;
    dist=(int **) malloc(matrixSize*sizeof(int *));

    for(int i=0;i<matrixSize;i++)
   dist[i]=(int *) malloc(matrixSize*sizeof(int));
*/
    int dist[700][700],n;
    ifstream in("royfloyd.in");
    ofstream out("royfloyd.out");

    in>>n;
    for(int i=1;i<=n;++i){
        for(int j=1;j<=n;++j){
            in>>dist[i][j];
            if(dist[i][j]==0 && i!=j)
                dist[i][j]=inf;
        }
    }
    clock_t t;
    t = clock();
    for(int k1=1;k1<=1;++k1){
    for(int k=1;k<=n;++k){
        for(int i=1;i<=n;++i)
            for(int j=1;j<=n;++j)
                if(dist[i][j]>dist[i][k]+dist[k][j] && dist[i][k]!=inf && dist[k][j]!=inf && i!=k && j!=k)//daca dist de la i la j prin k este mai mica
                    dist[i][j]=dist[i][k]+dist[k][j];
    }
    }

    t = clock() - t;

   cout<<" execution time: "<<((double)t)/CLOCKS_PER_SEC<<endl;

    for(int i=1;i<=n;++i){
        for(int j=1;j<=n;++j){
            if(dist[i][j]==inf)
                out<<"0 ";
            else
                out<<dist[i][j]<<" ";
        }
        out<<"\n";
    }
    /*
    int inputSize=700;
     out<<inputSize<<endl;
        for(int i=1;i<=inputSize;++i){
            for(int j=1;j<=inputSize;++j){
                if(i==j){
                    out<<"0 ";
                }
                else{
                   int r= rand() % inputSize;
                    out<<r<<" ";
                }
            }
            out<<endl;
        }
*/
    return 0;
}
