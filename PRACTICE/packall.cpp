#include <stdio.h>
int main ()
{
  long long c[1000];
  long long n, i, j;
  scanf ("%llu", &n);
  for (i = 1; i <= n; i++)
    c[i] = 0;
    c[0] = 1;
  for (j = 1; j <= n; j++)
    for (i = j; i >= 1; i--)
      c[i] = c[i - 1] + c[i];
  for (i = 0; i <= n; i++)
  printf ("%llu ", c[i]);
  return 0;
}