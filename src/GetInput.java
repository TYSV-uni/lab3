import java.util.Scanner;

interface GetInput
{
    static String get_string(String prompt)
    {
        String x;
        Scanner sc = new Scanner(System.in);

        while (true)
            try
            {
                System.out.print(prompt);
                x = sc.nextLine();
                return x;
            }
            catch (Exception e)
            {
                System.out.println("Invalid input");
            }
    }

    static int get_int_in_range(String prompt, int min, int max)
    {
        int x;
        Scanner sc = new Scanner(System.in);

        while (true)
            try
            {
                System.out.print(prompt);
                x = Integer.parseInt(sc.nextLine());
                if (!(x >= min))
                {
                    System.out.println("Input should not be smaller than " + min);
                    continue;
                }
                else if (!(x <= max))
                {
                    System.out.println("Input should not be bigger than " + max);
                    continue;
                }
                return x;
            }
            catch(Exception e)
            {
                System.out.println("Invalid input");
            }
    }

    static double get_double_in_range(String prompt, double min, double max)
    {
        double x;
        Scanner sc = new Scanner(System.in);

        while (true)
            try
            {
                System.out.print(prompt);
                x = Double.parseDouble(sc.nextLine());
                if (!(x > min))
                {
                    System.out.println("Input should be bigger than " + min);
                    continue;
                }
                else if (!(x < max))
                {
                    System.out.println("Input should be smaller than " + max);
                    continue;
                }
                return x;
            }
            catch(Exception e)
            {
                System.out.println("Invalid input");
            }
    }
}
