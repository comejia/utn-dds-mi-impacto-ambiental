package dominio.miembros;

import dominio.organizaciones.SectorTerritorial;

public class AgenteSectorial {
  private SectorTerritorial sectoresTerritoriales;

  public AgenteSectorial() {}

  public AgenteSectorial(SectorTerritorial sectoresTerritoriales) {
    this.sectoresTerritoriales = sectoresTerritoriales;
  }

  public SectorTerritorial getSector() {
    return this.sectoresTerritoriales;
  }

}
