class HuffmanNode {
    byte data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(byte data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }
}
