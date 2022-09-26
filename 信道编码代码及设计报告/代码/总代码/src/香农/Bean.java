package œ„≈©;

public class Bean {
    private String key;
    private int count;
    private double p;
    private double _logp;
    private int codingLength;
    private double cumulativeprobability;
    private String codeLetter;
    public Bean(String key, int count) {
        this.key = key;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double get_logp() {
        return _logp;
    }

    public void set_logp(double _logp) {
        this._logp = _logp;
    }

    public int getCodingLength() {
        return codingLength;
    }

    public void setCodingLength(int codingLength) {
        this.codingLength = codingLength;
    }

    public double getCumulativeprobability() {
        return cumulativeprobability;
    }

    public void setCumulativeprobability(double cumulativeprobability) {
        this.cumulativeprobability = cumulativeprobability;
    }

    public String getCodeLetter() {
        return codeLetter;
    }

    public void setCodeLetter(String codeLetter) {
        this.codeLetter = codeLetter;
    }
}
