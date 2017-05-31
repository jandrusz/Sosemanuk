package com.sosemanuk.algorithm;

/**
 * Klasa zawierająca sBox'y.
 */
class SerpentBitsliceSBox {

    /**
     * Funkcja określająca który sBox zostanie wybrany
     *
     * @param index numer sBoxa
     * @param r0    wejście nr 0
     * @param r1    wejście nr 1
     * @param r2    wejście nr 2
     * @param r3    wejście nr 3
     * @param r4    wejście nr 4
     * @return zwracana wartość
     */
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

    /**
     * sBox nr 0
     *
     * @param r0 wejście nr 0
     * @param r1 wejście nr 1
     * @param r2 wejście nr 2
     * @param r3 wejście nr 3
     * @param r4 wejście nr 4
     * @return zwracana wartość
     */
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

    /**
     * sBox nr 1
     *
     * @param r0 wejście nr 0
     * @param r1 wejście nr 1
     * @param r2 wejście nr 2
     * @param r3 wejście nr 3
     * @param r4 wejście nr 4
     * @return zwracana wartość
     */
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

    /**
     * sBox nr 2
     *
     * @param r0 wejście nr 0
     * @param r1 wejście nr 1
     * @param r2 wejście nr 2
     * @param r3 wejście nr 3
     * @param r4 wejście nr 4
     * @return zwracana wartość
     */
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

    /**
     * sBox nr 3
     *
     * @param r0 wejście nr 0
     * @param r1 wejście nr 1
     * @param r2 wejście nr 2
     * @param r3 wejście nr 3
     * @param r4 wejście nr 4
     * @return zwracana wartość
     */
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

    /**
     * sBox nr 4
     *
     * @param r0 wejście nr 0
     * @param r1 wejście nr 1
     * @param r2 wejście nr 2
     * @param r3 wejście nr 3
     * @param r4 wejście nr 4
     * @return zwracana wartość
     */
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

    /**
     * sBox nr 5
     *
     * @param r0 wejście nr 0
     * @param r1 wejście nr 1
     * @param r2 wejście nr 2
     * @param r3 wejście nr 3
     * @param r4 wejście nr 4
     * @return zwracana wartość
     */
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

    /**
     * sBox nr 6
     *
     * @param r0 wejście nr 0
     * @param r1 wejście nr 1
     * @param r2 wejście nr 2
     * @param r3 wejście nr 3
     * @param r4 wejście nr 4
     * @return zwracana wartość
     */
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

    /**
     * sBox nr 7
     *
     * @param r0 wejście nr 0
     * @param r1 wejście nr 1
     * @param r2 wejście nr 2
     * @param r3 wejście nr 3
     * @param r4 wejście nr 4
     * @return zwracana wartość
     */
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
