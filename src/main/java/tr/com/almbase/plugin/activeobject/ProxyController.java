package tr.com.almbase.plugin.activeobject;

public interface ProxyController {
	Proxy[] getAllEntriesFromAOTable();
	Proxy createRecordInAOTable(ProxyObject proxyObject);
	void deleteRecordFromAOTable(Proxy proxy);
}