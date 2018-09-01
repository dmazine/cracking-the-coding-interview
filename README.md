# Cranking the Coding Interview Study Notes

## Big O

### Time Complexity

Big O time is the language metric we use to describe the efficiency of algorithms.

No matter how big the constant is and how slow the linear increase is, linear will at some point surpass constant.

#### Big O, Big Theta, and Big Omega

Academics use big O, Big Theta, and big Omega to describe runtimes.

- Big O: describes an upper bound on time. Ex: An algorithm that prints all the values in an array could be described as
O(N), but also as O(Nˆ2), O(Nˆ3), or O(2ˆn) (or many other big O times) since **the algorithm is as fast as as each of
these**.

- Big Omega: is the equivalent concept but for the lower bound. Ex: Printing values in an arrays is Omega(N), as well as
Omega(log N) and Omega(1) since **the algorithm won't be faster than those runtimes**.

- Big Theta: means both O and Omega. **An algorithm is Theta(N) if it is both O(N) and Omega(N)**.

*Industry's meaning of big O is closer to what academics mean by Theta, in that it would be seen as incorrect to
describe printing an array as O(Nˆ2). Industry would just say this is O(N).*

Quick Sort Algorithm:

    Best Case: O(N)
    Worst Case: O(Nˆ2)
    Expected Case: O(N log N)

### Space Complexity

Time is not the only thing that matters in an algorithm. We might also case about the amount of memory -- or space --
required by an algorithm.

### Multi-Part Algorithms: Add vs. Multiply

- If your algorithm is the form "do this, the when you're all done, do that" then you add the runtimes.

- If your algorithm is in the form "do this for each time you do that" then you multiply the runtimes.

### Amortized Time

When the array hits capacity, the ArrayList class will create a new array with double the capacity and copy all the
elements over to the new array.

If the array contains N elements, inserting a new one will taken O(N).

I the array is not full, the insertion will take O(1) time.

It allows us to describe that this worst case happens every once in a while, but once it happens, it won't happen again
for so long that the cost is amortized.

When inserting we double the capacity when the size of th array is power of 2. So after X elements, we double the
capacity at array sizes 1, 2, 4, ... X, which means 1, 2, 4, ... X copies.

Since 1 + 2 + 4 + 8 + ... + X is roughly 2X, X insertions take O(2X) time. The amortized time for each insertion is O(1).

### Log N Runtimes

When you see a problem where the number of elements in the problem space gets halved each time, that will likely be a
O (log N) runtime.

### Recursive Runtimes

When you have a recursive function that makes multiple calls, the runtime will often (but not always) look like
O(branchesˆdepth), where branches is teh number of times each recursive call branches.

```
int f(int n) {
    if (n <= 1) {
        return 1;
    }
    return f(n - 1) + f(n - 1);
}
```

Runtime of the code above is O(2ˆN).

> Sum of Powers of 2
>
> Consider the sequence (2 ^ 0) + (2 ^ 1) + (2 ^ 2) + ... + (2 ^ N). What is the result?
>
> |    |Power        |Binary |Decimal   
> |----|-------------|-------|----------
> |    | 2 ^ 0       | 00001 | 1
> |    | 2 ^ 1       | 00010 | 2
> |    | 2 ^ 2       | 00100 | 4
> |    | 2 ^ 3       | 01000 | 8
> |    | 2 ^ 4       | 10000 | 16
> |sum:| (2 ^ 5) - 1 | 11111 | 32 - 1 = 31
>
> Therefore, the sum of (2 ^ 0) + (2 ^ 1) (2 ^ 2) + ... + (2 ^ N) is (2 ^ (N + 1)) - 1 

The base of a log does not matter for big O since logs of different bases are only different by a constant factor.
However, this does not apply to exponents. The base of an exponent does matter.

### Examples:

1. What is the runtime of the code below?

    ```
    void foo(int[] array) {
        int sum = 0;
        int product = 1;
        for (int i = 0; i < array.length; i++) {
            sum += array[1];
        }
        for (int i = 0; i < array.length; i++) {
            product *= array[1];
        }
        System.out.println(sum + ", " + product);
    }
    ```
    
    This will take O(N) time. The fact we iterate through the array twice does not matter.

1. What is the runtime of the code below?

    ```
    void printPairs(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.println(array[i] + ", " + array[j]);
            }
        }
    }    
    ```
    
    The inner for loop has O(N) iterations and it is called N times, so the runtime is O(Nˆ2).
    
    *Alternative way of think: It is printing all pairs (two-element sequences). There are O(Nˆ2) pairs; therefore,
    the runtime is O(Nˆ2).*
    

1. What is the runtime of the code below?

    ```
    void printUnorderedPairs(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                System.out.println(array[i] + ", " + array[j]);
            }
        }
    }
    ```
    
    The number of steps total is: (N - 1) + (N - 2) + (N - 3) + ... + 2 + 1 = (N / 2) * (N + 1). Thus the runtime will be
    O(N ˆ 2).
    
    > Sum of integers 1 through N
    > 
    > What is t1 + 2 + 3 + ... (N - 3) + (N - 2) + N?
    >
    > If we pair 1 with N, 2 with (N - 2), 3 with (N - 2) and so on.
    >
    > (N + 1) + (2 + (N - 1)) + (3 + (N - 2)) ... = (N / 2) * (N - 1)

    *Alternative way of think: It iterates through each pair of values for (i, j) where j is bigger than i. There are
    Nˆ2 total pairs. Roughly half of those will have i < j and the remaining halpf will have i > j. This code goes
    through roughly (N ^ 2) / 2 pairs so O(N ^2) work.*
    
1. What is the runtime of the code below?

    ```
    void printUnorderedPairs(int[] arrayA, int[] arrayB) {
        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayB.length; j++) {
                if (arrayA[i] < arrayB[j]) {
                    System.out.println(arrayA[i] + ", " + arrayB[j]);
                }
            }
        }
    }                
    ```
    
    The if-statement is within j's for loop is O(1) time since it's just a sequence of constant-time statements.
    
    For each element of arrayA, the inner for loop goes through b iterations, where b = arrayB.length. If
    a = arrayA.length, then the runtime is O(a * b).

1. What is the runtime of the code below?

    ```
    void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int other = array.length - i - 1;
            int temp = array[i];
            array[i] = arrayj[other];
            array[other] = temp;
        }
    }       
    ```
    
    This algorithm runs in O(N) time. The fact that it only goes through half of the array (in terms of iterations) does
    not impact the big O time.

1. Which of the following are equivalent to O(N)?

    * O (N + P), where P < N /2
    
        Equivalent - if P < N / 2, N is the dominant term so we can drop O(P).
    
    * O(2N)
    
        Equivalent - we can drop constants.
    
    * O (N + log N)
    
        Equivalent - O(N) dominates O(log N), so we can drop O(log N).
    
    * O (N + M)
    
        Not equivalent - There is no established relationship between N amd M, so we have to keep both variables.

1. Suppose we had an algorithm that took in an array of strings, sorted each string, and then sorted the full array.
What would the runtime be?

    * Let s be the length of the longest string.
    * Let a be the length of the array.
    
    Now we can work through this is parts:
    
    * Sorting each string is O(s log s).
    
    * We have to do this for every string in the array, so that is O(a * s log s).
    
    * Now we have to sort the strings in the array and we should take into account that we need to compare the strings.
    Each string comparison takes O(s) time. There are O(a log a) comparisons, therefore this will take O(s * a log a)
    time.
    
    This means, O((a * s log s) + (s * a log a)) or O((a * s)(log s + log a)) time. 

1. What is the runtime of the code below?

    ```
    ```

1. What is the runtime of the code below?

    ```
    ```
1. What is the runtime of the code below?

    ```
    ```

1. What is the runtime of the code below?

    ```
    ```

1. What is the runtime of the code below?

    ```
    ```

1. What is the runtime of the code below?

    ```
    ```

