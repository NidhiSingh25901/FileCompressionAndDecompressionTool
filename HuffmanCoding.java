import java.util.HashMap;

class HuffmanCoding {
    private final HashMap<Byte, String> huffmanCodes = new HashMap<>();

    public HuffmanCoding(HuffmanNode root) {
        buildCodes(root, "");
    }

    private void buildCodes(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.data, code);
        }
        buildCodes(node.left, code + "0");
        buildCodes(node.right, code + "1");
    }

    public String encode(byte data) {
        return huffmanCodes.get(data);
    }

    public HashMap<Byte, String> getCodes() {
        return huffmanCodes;
    }
}
