# Code Atlas — Contexto para IA: StramingSong

> Digest gerado pelo Code Atlas em 2026-06-11 03:22 UTC. Não edite à mão — é regenerado a cada `codeatlas scan`. Consulte os dados detalhados via servidor MCP (`codeatlas`), **não** lendo os JSONs crus de `.codeatlas/` (custosos em tokens).

## Arquitetura
- **Nós:** 921 · **Arestas:** 1070
- **Tipos dominantes:** import (373), method (271), variable (118), file (69), class (51), endpoint (20)
- **Clusters (comunidades funcionais):**
    - infra (79 nós) — predominância: method
    - model (77 nós) — predominância: method
    - model (71 nós) — predominância: method
    - api (42 nós) — predominância: method
    - model (41 nós) — predominância: method
    - api (36 nós) — predominância: method
    - infra (11 nós) — predominância: method
    - model (11 nós) — predominância: method
- **Endpoints REST mapeados:** 20
    - `DELETE /api/playlists/{playlistId}/musicas/{musicaId}`
    - `DELETE /api/usuarios/{usuarioId}/assinaturas/{assinaturaId}`
    - `DELETE /api/usuarios/{usuarioId}/favoritos/{musicaId}`
    - `GET /api/musicas`
    - `GET /api/musicas/artista/{artista}`
    - `GET /api/musicas/{id}`
    - … (+14 mais — use `atlas_list_nodes`)

## Indicadores e hotspots
- **Redução de tokens (mapa vs. fonte):** 44.3%
- **AI Readiness:** 100/100  (tipagem 100%, docs 100%, tamanho 100%, acoplamento 100%, testes 100%)
- **Tech-Debt contextual:** 0/100

## Workflow de refatoração (use as ferramentas MCP `codeatlas`)

Não leia `graph.json`/`*.cytoscape.json` — consulte sob demanda:

1. **Orientar-se:** `atlas_status` — visão geral do projeto.
2. **Triar:** `atlas_quality_issues(severity="critical")` — comece pelos hotspots acima.
3. **Localizar:** `atlas_search("<descrição do comportamento>")` ou `atlas_get_file("<arquivo>")` para achar o nó-alvo (ID `tipo:arquivo::nome`).
4. **Contexto mínimo:** `atlas_context_pack(node_id, task="modify", budget=1200)` — traz só callers/callees/impactos/riscos/testes que cabem no orçamento de tokens.
5. **Medir o risco ANTES de mexer:** `atlas_impact_analysis(node_id)` — raio de explosão (callers diretos/indiretos, arquivos dependentes, testes que cobrem).
6. **Aplicar a mudança** com esse contexto; rode os testes apontados.
7. **Validar ganho:** re-scan e compare `atlas_readiness` (AI Readiness deve subir, Tech-Debt cair).
8. **Registrar decisão:** `atlas_memory_store(type="decision", content=...)` — persiste o porquê para sessões futuras.

## Mantendo o atlas atualizado

Os dados acima refletem o último scan. Após mudanças relevantes, regenere:

```bash
codeatlas scan "C:\Faculdade\DDD\AT\StramingSong\StramingSong"
```

Isso reconstrói o grafo e regenera `token_index.json`, `readiness.json` e este digest. Para visualização interativa: `codeatlas serve "C:\Faculdade\DDD\AT\StramingSong\StramingSong" --port 3009`.
