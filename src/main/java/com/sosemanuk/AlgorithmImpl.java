package com.sosemanuk;

class AlgorithmImpl implements Algorithm {

    private SosemanukState sosemanukState;

    private SosemanukMasterState sosemanukMasterState;

    AlgorithmImpl() {
        sosemanukState = new SosemanukState();
        sosemanukMasterState = new SosemanukMasterState();
    }

    public void initializeMasterStateWithKey(int key, int size) {

    }

    public void initializeWithInitializationVector(byte initializationVector) {

    }

    public void extract(byte stream) {

    }

    public void sBoxApply(Integer sBoxIndex, Integer[] in, Integer[] out) {
//        switch (sBoxIndex) {
//            case 0: {
//                SerpentBitsliceSBox.sb0(in[0], in[1], in[2], in[3], out[0], out[1], out[2], out[3]);
//                break;
//            }
//            case 1: {
//                SerpentBitsliceSBox.sb1(in[0], in[1], in[2], in[3], out[0], out[1], out[2], out[3]);
//                break;
//            }
//            case 2: {
//                SerpentBitsliceSBox.sb2(in[0], in[1], in[2], in[3], out[0], out[1], out[2], out[3]);
//                break;
//            }
//            case 3: {
//                SerpentBitsliceSBox.sb3(in[0], in[1], in[2], in[3], out[0], out[1], out[2], out[3]);
//                break;
//            }
//            case 4: {
//                SerpentBitsliceSBox.sb4(in[0], in[1], in[2], in[3], out[0], out[1], out[2], out[3]);
//                break;
//            }
//            case 5: {
//                SerpentBitsliceSBox.sb5(in[0], in[1], in[2], in[3], out[0], out[1], out[2], out[3]);
//                break;
//            }
//            case 6: {
//                SerpentBitsliceSBox.sb6(in[0], in[1], in[2], in[3], out[0], out[1], out[2], out[3]);
//                break;
//            }
//            case 7: {
//                SerpentBitsliceSBox.sb7(in[0], in[1], in[2], in[3], out[0], out[1], out[2], out[3]);
//                break;
//            }
//        }
    }

    public Integer lfsrStep() {
        Integer[] s = sosemanukState.s;
        final Integer t = sosemanukState.t;

        Integer st0 = s[t];
        Integer st3 = s[(t + 3) % 10];

        s[t] = s[(t + 9) % 10]
                ^ (st3 >> 8) ^ AlphaOperations.divisionByAlpha[st3 & 0xff]
                ^ (st0 << 8) ^ AlphaOperations.muliplicationByAlpha[st0 >> 24];

        sosemanukState.t = (t + 1) % 10;
        return st0;
    }

    public void serpentRound(Integer index, Integer[] data) {
        int i;
        Integer[] tmp = new Integer[4];

//        for (i = 0; i < 4; ++i) {
//            tmp[i] = data[i] ^ master -> k[index * 4 + i];
//        }
//
//        sBoxApply(index % 8, tmp, data);
//
//        data[0] = rotl(data[0], 13);
//        data[2] = rotl(data[2], 3);
//        data[1] = data[1] ^ data[0] ^ data[2];
//        data[3] = data[3] ^ data[2] ^ (data[0] << 3);
//        data[1] = rotl(data[1], 1);
//        data[3] = rotl(data[3], 7);
//        data[0] = data[0] ^ data[1] ^ data[3];
//        data[2] = data[2] ^ data[3] ^ (data[1] << 7);
//        data[0] = rotl(data[0], 5);
//        data[2] = rotl(data[2], 22);
    }

    public Integer automatonStep() {
        Integer[] r = sosemanukState.r;
        Integer[] s = sosemanukState.s;
        Integer r0_prev = r[0];
        Integer t = sosemanukState.t;

//        r[0] = r[1] + ((r0_prev & 1 u) ?s[(t + 1) % 10] ^ s[(t + 8) % 10] :s[(t + 1) % 10]);
//        r[1] = rotl(r0_prev * 0x54655307, 7);

        return (s[(t + 9) % 10] + r[0]) ^ r[1];
    }

    public void start() {
        System.out.println("Computing");
    }

}
