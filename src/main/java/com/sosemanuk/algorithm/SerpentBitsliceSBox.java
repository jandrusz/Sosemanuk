package com.sosemanuk.algorithm;

class SerpentBitsliceSBox {


    static int[] sBox(int index, int r0, int r1, int r2, int r3, int r4) {
        switch (index) {
            case 0:
                return sb0(r0, r1, r2, r3, r4);
            case 1:
                return sb1(r0, r1, r2, r3, r4);
            case 2:
                return sb2(r0, r1, r2, r3, r4);
            case 3:
                return sb3(r0, r1, r2, r3, r4);
            case 4:
                return sb4(r0, r1, r2, r3, r4);
            case 5:
                return sb5(r0, r1, r2, r3, r4);
            case 6:
                return sb6(r0, r1, r2, r3, r4);
            case 7:
                return sb7(r0, r1, r2, r3, r4);
            default:
                return new int[]{};
        }
    }

    static int[] sb0(int r0, int r1, int r2, int r3, int r4) {
        r3 ^= r0;
        r4 = r1;
        r1 &= r3;
        r4 ^= r2;
        r1 ^= r0;
        r0 |= r3;
        r0 ^= r4;
        r4 ^= r3;
        r3 ^= r2;
        r2 |= r1;
        r2 ^= r4;
        r4 = ~r4;
        r4 |= r1;
        r1 ^= r3;
        r1 ^= r4;
        r3 |= r0;
        r1 ^= r3;
        r4 ^= r3;

        return new int[]{r1, r4, r2, r0};
    }

    static int[] sb1(int r0, int r1, int r2, int r3, int r4) {
        r0 = ~r0;
        r2 = ~r2;
        r4 = r0;
        r0 &= r1;
        r2 ^= r0;
        r0 |= r3;
        r3 ^= r2;
        r1 ^= r0;
        r0 ^= r4;
        r4 |= r1;
        r1 ^= r3;
        r2 |= r0;
        r2 &= r4;
        r0 ^= r1;
        r1 &= r2;
        r1 ^= r0;
        r0 &= r2;
        r0 ^= r4;

        return new int[]{r2, r0, r3, r1};
    }

    static int[] sb2(int r0, int r1, int r2, int r3, int r4) {
        r4 = r0;
        r0 &= r2;
        r0 ^= r3;
        r2 ^= r1;
        r2 ^= r0;
        r3 |= r4;
        r3 ^= r1;
        r4 ^= r2;
        r1 = r3;
        r3 |= r4;
        r3 ^= r0;
        r0 &= r1;
        r4 ^= r0;
        r1 ^= r3;
        r1 ^= r4;
        r4 = ~r4;

        return new int[]{r2, r3, r1, r4};
    }

    static int[] sb3(int r0, int r1, int r2, int r3, int r4) {
        r4 = r0;
        r0 |= r3;
        r3 ^= r1;
        r1 &= r4;
        r4 ^= r2;
        r2 ^= r3;
        r3 &= r0;
        r4 |= r1;
        r3 ^= r4;
        r0 ^= r1;
        r4 &= r0;
        r1 ^= r3;
        r4 ^= r2;
        r1 |= r0;
        r1 ^= r2;
        r0 ^= r3;
        r2 = r1;
        r1 |= r3;
        r1 ^= r0;

        return new int[]{r1, r2, r3, r4};
    }

    static int[] sb4(int r0, int r1, int r2, int r3, int r4) {
        r1 ^= r3;
        r3 = ~r3;
        r2 ^= r3;
        r3 ^= r0;
        r4 = r1;
        r1 &= r3;
        r1 ^= r2;
        r4 ^= r3;
        r0 ^= r4;
        r2 &= r4;
        r2 ^= r0;
        r0 &= r1;
        r3 ^= r0;
        r4 |= r1;
        r4 ^= r0;
        r0 |= r3;
        r0 ^= r2;
        r2 &= r3;
        r0 = ~r0;
        r4 ^= r2;

        return new int[]{r1, r4, r0, r3};
    }

    static int[] sb5(int r0, int r1, int r2, int r3, int r4) {
        r0 ^= r1;
        r1 ^= r3;
        r3 = ~r3;
        r4 = r1;
        r1 &= r0;
        r2 ^= r3;
        r1 ^= r2;
        r2 |= r4;
        r4 ^= r3;
        r3 &= r1;
        r3 ^= r0;
        r4 ^= r1;
        r4 ^= r2;
        r2 ^= r0;
        r0 &= r3;
        r2 = ~r2;
        r0 ^= r4;
        r4 |= r3;
        r2 ^= r4;

        return new int[]{r1, r3, r0, r2};
    }

    static int[] sb6(int r0, int r1, int r2, int r3, int r4) {
        r2 = ~r2;
        r4 = r3;
        r3 &= r0;
        r0 ^= r4;
        r3 ^= r2;
        r2 |= r4;
        r1 ^= r3;
        r2 ^= r0;
        r0 |= r1;
        r2 ^= r1;
        r4 ^= r0;
        r0 |= r3;
        r0 ^= r2;
        r4 ^= r3;
        r4 ^= r0;
        r3 = ~r3;
        r2 &= r4;
        r2 ^= r3;

        return new int[]{r0, r1, r4, r2};
    }

    static int[] sb7(int r0, int r1, int r2, int r3, int r4) {
        r4 = r1;
        r1 |= r2;
        r1 ^= r3;
        r4 ^= r2;
        r2 ^= r1;
        r3 |= r4;
        r3 &= r0;
        r4 ^= r2;
        r3 ^= r1;
        r1 |= r4;
        r1 ^= r0;
        r0 |= r4;
        r0 ^= r2;
        r1 ^= r4;
        r2 ^= r1;
        r1 &= r0;
        r1 ^= r4;
        r2 = ~r2;
        r2 |= r0;
        r4 ^= r2;

        return new int[]{r4, r3, r1, r0};
    }
}
