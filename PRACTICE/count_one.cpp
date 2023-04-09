#include <stdio.h>
int main(){
    int x;
   scanf("%d",&x);
    int w = 0;
    while (x > 0){
        w = w + (x & 1);
        x = x >> 1;
    }
    printf("w = %d", w);
}

