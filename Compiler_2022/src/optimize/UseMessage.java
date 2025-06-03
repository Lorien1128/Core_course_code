package optimize;

public class UseMessage {
    private final BaseBlock baseBlock;
    private final int index;

    public UseMessage(BaseBlock baseBlock, int index) {
        this.baseBlock = baseBlock;
        this.index = index;
    }

    public BaseBlock getBaseBlock() {
        return baseBlock;
    }

    public int getIndex() {
        return index;
    }
}
