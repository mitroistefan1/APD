#include <stdio.h>
#include <stdlib.h>
#include <omp.h>


void mm(double *A, double *B, double *C, int n)
{
    int i, j, k;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            double result  = 0;
            for (k = 0; k < n; k++) {
                result += A[i*n+k]*B[k*n+j];
            }
            C[i*n+j ] = result;
        }
    }
}

void mm_omp(double *A, double *B, double *C, int n)
{
    #pragma omp parallel
    {
        int i, j, k;
        #pragma omp for
        for (i = 0; i < n; i++) {
            printf("Row: %d Thread with ID: %d \n",i,omp_get_thread_num());
            for (j = 0; j < n; j++) {
                double result  = 0;
                for (k = 0; k < n; k++) {
                    result += A[i*n+k]*B[k*n+j];
                }
                C[i*n+j ] = result;
            }
        }

    }
}


int main() {
    int i, n;
    double *A, *B, *C, multiplyTime;

    n=512;

    A = (double*)malloc(sizeof(double)*n*n);
    B = (double*)malloc(sizeof(double)*n*n);
    C = (double*)malloc(sizeof(double)*n*n);

    for(i=0; i<n*n; i++){
        A[i] = (double)rand() / (double)((unsigned)RAND_MAX + 1);
        B[i] = (double)rand() / (double)((unsigned)RAND_MAX + 1);
    }

    multiplyTime = omp_get_wtime();
    mm_omp(A,B,C, n);
    multiplyTime = omp_get_wtime() - multiplyTime;
    printf("OPENMP matrix multiplication time :%f\n", multiplyTime);

    multiplyTime = omp_get_wtime();
    mm(A,B,C, n);
    multiplyTime = omp_get_wtime() - multiplyTime;
    printf("matrix multiplication time: %f\n", multiplyTime);


    return 0;

}
