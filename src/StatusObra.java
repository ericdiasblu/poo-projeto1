public enum StatusObra {
    PLANEJADA,
    EM_ANDAMENTO,
    CONCLUIDA,
    CANCELADA;

    public boolean podeTransicionarPara(StatusObra novoStatus) {
        if (novoStatus == null) {
            return false;
        }

        switch (this) {
            case PLANEJADA:
                return novoStatus == EM_ANDAMENTO || novoStatus == CANCELADA;
            case EM_ANDAMENTO:
                return novoStatus == CONCLUIDA || novoStatus == CANCELADA;
            default:
                return false;
        }
    }
}
