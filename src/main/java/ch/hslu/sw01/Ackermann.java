package ch.hslu.sw01;

class Ackermann
{
    public static long ackermann( long n, long m )
    {
        if ( n == 0 )
            return m + 1;
        else

        if ( m == 0 )
            return ackermann( n - 1, 1 );
        else
            return ackermann( n - 1, ackermann(n, m - 1) );

    }
    public static void main( String args[] )
    {
        int x = 2,
                y = 2;
        System.out.println( "ackermann(" + x + "," + y + ")=" + ackermann(x,y) );
    }
}