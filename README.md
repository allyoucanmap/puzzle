# puzzle

Mix of java classes:
- osmReader -> osm data import
``` java
  OSMData oData = OSMLoader.loadByURL("api url");
  List<Poly> buildings = oData.getByTag("building");
```
