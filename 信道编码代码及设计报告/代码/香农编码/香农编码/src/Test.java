public class Test {
    public static void main(String[] args) {
        ShannonEncoding shannonEncoding = new ShannonEncoding();
        shannonEncoding.getProbability();
        shannonEncoding.codeLetter();
        System.out.println("平均码长:"+shannonEncoding.averageCodingLength());
        System.out.println("编码效率:"+shannonEncoding.codeEfficiency());
    }
}
