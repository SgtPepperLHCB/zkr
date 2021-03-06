package zkr

import org.apache.zookeeper.txn.DeleteTxn
import org.apache.zookeeper.txn.TxnHeader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import zkr.ZNodeTxn.Companion.txn2String
import java.lang.invoke.MethodHandles

class ZNodeTxnDelete(override val options: ZkrOptions, override val zk: ZkClient, private val restore: Boolean = false) : ZNodeTxn<DeleteTxn> {

    override fun process(hdr: TxnHeader, txn: DeleteTxn?) {
        val txnString = txn2String(hdr, txn)
        val s = "$txnString\n  path = ${txn?.path}"
        if (txn != null) {
            if (options.isPathExcluded(txn.path)) {
                ZNodeTxnCreate.logger.info("EXCLUDE: txn=${txn.javaClass.simpleName}, path=${txn.path}")
                return
            }
            logger.debug(s)

            if (!restore) {
                logger.info("PRETEND: txn=${txn.javaClass.simpleName}, path=${txn.path}")
                return
            }

            zk.deleteZNode(txn.path)
            logger.info("Deleted znode at path=${txn.path}")
        } else {
            logger.warn("Cannot delete null txn: $txnString")
        }
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
    }
} //-ZNodeTxnDelete