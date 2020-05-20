    public static void olympiaad(int[][] twoIntArray) {
        int i, smallerInt, biggerInt, counter, maxCounter = 0, maxInt0 = 0, maxInt1 = 0;

        for (i = 0; i < twoIntArray.length; i++) {
            if (twoIntArray[i][0] < twoIntArray[i][1]) {
                smallerInt = twoIntArray[i][0];
                biggerInt = twoIntArray[i][1];
            } else {
                smallerInt = twoIntArray[i][1];
                biggerInt = twoIntArray[i][0];
            }
            while (smallerInt <= biggerInt) {
                counter = olympiaad_cyclic_lenth(smallerInt);
                smallerInt++;
                if (maxCounter < counter) {
                    maxCounter = counter;
                    maxInt0 = twoIntArray[i][0];
                    maxInt1 = twoIntArray[i][1];
                }
            }
            System.out.println(maxInt0 + " " + maxInt1 + " " + maxCounter);
            maxCounter = 0;
        }
    }

    public static int olympiaad_cyclic_lenth(int a) {
        return olympiaad_cyclic_lenth(a, 1);
    }

    public static int olympiaad_cyclic_lenth(int a, int counter) {
        //System.out.println("counter: " + counter + " | a: " + a);
        if (a != 1) {
            if (a % 2 == 1) {
                a = 3 * a + 1;
            } else {
                a = a / 2;
            }
            counter++;
            counter = olympiaad_cyclic_lenth(a, counter);
        }

        return counter;
    }