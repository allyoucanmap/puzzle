# puzzle

Mix of java classes:
- osmReader -> osm data import

  OSMData oData = OSMLoader.loadByURL("api url");
	List<Poly> buildings = oData.getByTag("building");
