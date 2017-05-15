package com.sosemanuk;

interface Algorithm {

    void initializeMasterStateWithKey(int key, int size);

    void initializeWithInitializationVector(byte initializationVector);

    void extract(byte stream);

    void sBoxApply(Integer sBoxIndex, Integer[] in, Integer[] out);

    Integer lfsrStep();

    void serpentRound(Integer index, Integer[] data);

    Integer automatonStep();

    void start();
}
