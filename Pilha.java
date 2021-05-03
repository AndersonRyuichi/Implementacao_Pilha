package pilha;

class Main
{
    public static void main(String[] args) throws Exception {
        Pilha p = new Pilha(3);
        p.empilhar(5);
        p.empilhar(4);
        p.empilhar(3);

        int d1 = p.desempilhar();
        int d2 = p.desempilhar();
        int topo = p.TopoPilha();

        System.out.println(" Desempilhado 1: " + d1);
        System.out.println(" Desempilhado 2: " + d2);
        System.out.println(" Topo: " + topo);

        //----------------------------------------

        ValidaExpressao ev = new ValidaExpressao();

        String exp1 = "({1-2) + 3}";
        String exp2 = "{(8+[5*3] )-10}";
        String exp3 = "(5(5*4[6-1]))";
        String exp4 = "((5[5*4[6-1]))*10)";
        String exp5 = "{{3*(2+5)}";
        String exp6 = "}";

        boolean ev1 = ev.validar_uma_expressao(exp1);
        boolean ev2 = ev.validar_uma_expressao(exp2);
        boolean ev3 = ev.validar_uma_expressao(exp3);
        boolean ev4 = ev.validar_uma_expressao(exp4);
        boolean ev5 = ev.validar_uma_expressao(exp5);
        boolean ev6 = ev.validar_uma_expressao(exp6);

        System.out.println(exp1 + "  ->  " + ev1);
        System.out.println(exp2 + "  ->  " + ev2);
        System.out.println(exp3 + "  ->  " + ev3);
        System.out.println(exp4 + "  ->  " + ev4);
        System.out.println(exp5 + "  ->  " + ev5);
        System.out.println(exp6 + "  ->  " + ev6);
    }


}

class Pilha{
    private int topo;
    private int[] dados;

    Pilha(int TamanhoDaPilha) {
        topo = - 1;
        dados = new int[TamanhoDaPilha];
    }

    public int TopoPilha() throws Exception{ //Retorna o topo da pilha
        if (PilhaVazia()){
            throw new Exception("Nao foi possivel retornar um elemento da Pilha, ela se encontra vazia");
        }
        else{
            return dados[topo];
        }
    }

    public boolean PilhaVazia(){ //Verifica se a pilha esta cheia

        return topo == -1;
    }

    public boolean PilhaCheia(){ //Verifica se a pilha esta vazia

        return topo >= dados.length - 1;
    }

    public void empilhar(int elemento) throws Exception{ //Se a pilha estiver cheia aparece uma msg, sen ele empilha uma vez
        if (PilhaCheia()){
            throw new Exception("Pilha esta cheia");
        }
        else{
            topo = topo + 1;
            dados[topo] = elemento;
        }
    }

    public int desempilhar() throws Exception{ //Se a Pilha estiver vazia aparece uma msg, sen ele desempilha uma vez
        if (PilhaVazia()){
            throw new Exception("Pilha esta vazia");
        }
        else{
            int retorno_topo = dados[topo];
            topo = topo - 1;
            return retorno_topo;
        }
    }

}

class ValidaExpressao{
    private Pilha pilha;

    public boolean validar_uma_expressao(String expressao) throws Exception { //Valida a Expressao
        pilha = new Pilha(20);
        for (int e = 0 ; e <= expressao.length()-1 ; e++){
            char c = expressao.charAt(e);

            //Verifica se eh um caracter que abre (ex:`[`) ou fecha (ex:`]`)
            if (abreSimbolo(c)){
                pilha.empilhar((int)c);
            }
            else if (fechaSimbolo(c)){
//                System.out.println(c);
                if (pilha.PilhaVazia()){
                    return false;
                }
                int desempilhado = pilha.desempilhar();
                boolean isOposto = oposto((char) desempilhado, c);
                if (!isOposto){
                    return false;
                }
            }
        }

        return pilha.PilhaVazia();
    }

    public boolean abreSimbolo(char character){ //Characters que podem ser abertos

        return character == '(' || character == '[' || character == '{';
    }

    public boolean fechaSimbolo(char character){ //Characters que podem ser respectivamente fechados

        return character == ')' || character == ']' || character == '}';
    }

    public boolean oposto(char abrir, char fechar){ //Characters opostos respectivamente iguais

        return abrir == '(' && fechar == ')' || abrir == '[' && fechar == ']' || abrir == '{' && fechar == '}';
    }

}


