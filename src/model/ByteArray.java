package model;



import org.jbpm.bytes.ByteBlockChopper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * is a persistable array of bytes.  While there is no generic way of storing blobs
 * that is supported by many databases, all databases are able to handle small chunks
 * of bytes properly.  It is the responsibility of this class to chop the large byte
 * array into small chunks of 1K (and combine the chunks again in the reverse way).
 * Hibernate will persist the list of byte-chunks in the database.
 *
 * ByteArray is used in process variableInstances and in the file module (that stores the
 * non-parsed process archive files).
 */

/**
 * é uma matriz persistente de bytes.  Embora não exista uma maneira genérica de armazenar blobs
 * que é suportado por muitos bancos de dados, todos os bancos de dados são capazes de lidar com pequenos pedaços
 * de bytes corretamente.  É responsabilidade desta classe cortar o byte grande
 * organize em pequenos pedaços de 1K (e combine os pedaços novamente no sentido inverso).
 * O Hibernate persistirá a lista de blocos de bytes no banco de dados.
 *
 * ByteArray é usado em variáveis ​​de processo e no módulo de arquivo (que armazena o
 * arquivos de processo não analisados).
 *
 * Blob -> "Blob," que é a abreviação de Binary Large Object (objeto binário grande), é uma massa de dados em formato binário que não está necessariamente em conformidade com nenhum formato de arquivo.
 * O armazenamento de blobs mantém essas massas de dados em áreas de armazenamento não hierárquico chamadas lagos de dados.
 */
public class ByteArray implements Serializable {

    private static final long serialVersionUID = 1L;

    long id = 0;
    protected String name = null;
    protected List byteBlocks = null;

 private static final Log log =  LogFactory.getLog(ByteArray.class);

    public ByteArray() {
    }

    public ByteArray(byte[] bytes) {
        this.byteBlocks = ByteBlockChopper.chopItUp(bytes);
    }

    public ByteArray(String name, byte[] bytes) {
        this(bytes);
        this.name = name;

    /*
    if (log.isDebugEnabled()) {
      if (byteBlocks!=null) {
        logBlocks("chopping ");
      }
    }

    */
    }

    void logBlocks(String msg) {
        for(int blockIndex=0; blockIndex<byteBlocks.size(); blockIndex++) {
            byte[] block = (byte[]) byteBlocks.get(blockIndex);
            log.debug(msg+"["+block.length+"] "+StringUtil.toHexString(block));
        }
    }

    public ByteArray(ByteArray other) {
        List otherByteBlocks = other.getByteBlocks();
        if (otherByteBlocks!=null) {
            this.byteBlocks = new ArrayList(otherByteBlocks);
        }
        this.name = other.name;
    }

    public byte[] getBytes() {
    /*
    if (log.isDebugEnabled()) {
      if (byteBlocks!=null) {
        logBlocks("glueing ");
      }
    }
    */
        return ByteBlockChopper.glueChopsBackTogether(byteBlocks);
    }

    public long getId() {
        return id;
    }

    public boolean equals(Object o) {
        if (o==null) return false;
        if (! (o instanceof ByteArray)) return false;
        ByteArray other = (ByteArray) o;
        return Arrays.equals(ByteBlockChopper.glueChopsBackTogether(byteBlocks), ByteBlockChopper.glueChopsBackTogether(other.byteBlocks));
    }

    public int hashCode() {
        if (byteBlocks==null) return 0;
        return byteBlocks.hashCode();
    }

    public List getByteBlocks() {
        return byteBlocks;
    }
}
