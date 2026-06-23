# StramingSong — Documentação do Sistema

> Gerado automaticamente pelo **Code Atlas** em 2026-06-11 00:22  
> 921 nós · 1070 arestas · [Abrir no Visualizador](http://localhost:3002) · [Docs API](http://localhost:3002/api/docs)

## Visão Geral

**Resumo:** **69** arquivos · **51** classes · **20** endpoints REST · **271** métodos · **118** campos

**Tecnologias detectadas:** Lombok, REST API, Spring Boot


## Arquitetura em Camadas

```
**AssinaturaController**, **BibliotecaController**, **MusicaController**, **UsuarioController**, **TransacaoController**  (@RestController)

    down CALLS
**AssinaturaApplicationService**, **AssinaturaService**, **BibliotecaApplicationService**, **CatalogoApplicationService**, **UsuarioApplicationService**, **CadastroUsuarioService** (+2 mais)  (@Service)

    down CALLS
**AssinaturaRepositoryImpl**, **FavoritosRepositoryImpl**, **PlaylistRepositoryImpl**, **MusicaRepositoryImpl**, **UsuarioRepositoryImpl**, **TransacaoRepositoryImpl**  (@Repository)

    down USES
**Assinatura**, **Favoritos**, **Playlist**, **ReferenciaMusica**, **Musica**, **CartaoCredito** (+3 mais)  (Model/Entity)


DTOs: **CatalogoAdapter**

```

### Dependências entre Classes

| Classe | Depende de | Campo |

|--------|-----------|-------|

| AssinaturaController | AssinaturaApplicationService | `assinaturaService` |

| AssinaturaController | Assinatura | `assinatura` |

| AssinaturaApplicationService | AssinaturaRepository | `assinaturaRepository` |

| AssinaturaApplicationService | AssinaturaService | `servico` |

| AssinaturaApplicationService | Assinatura | `assinatura` |

| Assinatura | TipoPlano | `tipoPlano` |

| Assinatura | StatusAssinatura | `status` |

| AssinaturaService | AssinaturaRepository | `assinaturaRepository` |

| AssinaturaService | Assinatura | `assinatura` |

| AssinaturaRepositoryImpl | AssinaturaJpaRepository | `jpa` |

| CatalogoAdapter | MusicaRepository | `musicaRepository` |

| BibliotecaController | BibliotecaApplicationService | `bibliotecaService` |

| BibliotecaController | Playlist | `playlist` |

| BibliotecaApplicationService | PlaylistRepository | `playlistRepository` |

| BibliotecaApplicationService | FavoritosRepository | `favoritosRepository` |

| BibliotecaApplicationService | CatalogoPort | `catalogoPort` |

| BibliotecaApplicationService | Playlist | `playlist` |

| BibliotecaApplicationService | ReferenciaMusica | `referencia` |

| BibliotecaApplicationService | Favoritos | `favoritos` |

| FavoritosRepositoryImpl | FavoritosJpaRepository | `jpa` |

| PlaylistRepositoryImpl | PlaylistJpaRepository | `jpa` |

| MusicaController | CatalogoApplicationService | `catalogoService` |

| MusicaController | Musica | `musica` |

| CatalogoApplicationService | MusicaRepository | `musicaRepository` |

| CatalogoApplicationService | Musica | `musica` |

| Musica | Genero | `genero` |

| MusicaRepositoryImpl | MusicaJpaRepository | `jpa` |

| UsuarioController | UsuarioApplicationService | `usuarioService` |

| UsuarioController | Usuario | `usuario` |

| UsuarioController | CartaoCredito | `c` |

| UsuarioApplicationService | UsuarioRepository | `usuarioRepository` |

| UsuarioApplicationService | CriptografadorSenha | `criptografadorSenha` |

| UsuarioApplicationService | CadastroUsuarioService | `servico` |

| UsuarioApplicationService | Usuario | `usuario` |

| CartaoCredito | StatusCartao | `status` |

| Usuario | CartaoCredito | `cartaoCredito` |

| Usuario | Email | `emailValido` |

| Usuario | Senha | `senhaValida` |

| CadastroUsuarioService | UsuarioRepository | `usuarioRepository` |

| CadastroUsuarioService | CriptografadorSenha | `criptografadorSenha` |

| CadastroUsuarioService | Email | `emailDominio` |

| CadastroUsuarioService | Usuario | `usuario` |

| UsuarioRepositoryImpl | UsuarioJpaRepository | `jpa` |

| TransacaoController | AutorizacaoApplicationService | `autorizacaoService` |

| TransacaoController | Transacao | `transacao` |

| AutorizacaoApplicationService | TransacaoRepository | `transacaoRepository` |

| AutorizacaoApplicationService | UsuarioRepository | `usuarioRepository` |

| AutorizacaoApplicationService | Usuario | `usuario` |

| AutorizacaoApplicationService | Transacao | `transacao` |

| AutorizacaoApplicationService | AutorizacaoTransacaoService | `servico` |

| Transacao | StatusTransacao | `status` |

| AutorizacaoTransacaoService | TransacaoRepository | `transacaoRepository` |

| TransacaoRepositoryImpl | TransacaoJpaRepository | `jpa` |




## Endpoints REST

| Método | Path | Implementação | Chama |

|--------|------|---------------|-------|

| **GET** | `/api/musicas` | `listarTodas()` | `catalogoService.listarTodas` |

| **POST** | `/api/musicas` | `catalogar()` | `catalogoService.catalogarMusica` |

| **GET** | `/api/musicas/artista/{artista}` | `porArtista()` | `catalogoService.buscarPorArtista` |

| **GET** | `/api/musicas/{id}` | `buscarPorId()` | `catalogoService.buscarPorId` |

| **DELETE** | `/api/playlists/{playlistId}/musicas/{musicaId}` | `removerMusica()` | `bibliotecaService.removerMusicaDaPlaylist` |

| **POST** | `/api/playlists/{playlistId}/musicas/{musicaId}` | `adicionarMusica()` | `bibliotecaService.adicionarMusicaNaPlaylist` |

| **POST** | `/api/transacoes` | `autorizar()` | `autorizacaoService.autorizarTransacao` |

| **GET** | `/api/transacoes/usuario/{usuarioId}` | `listarPorUsuario()` | `autorizacaoService.listarTransacoesPorUsuario` |

| **GET** | `/api/usuarios` | `listarTodos()` | `usuarioService.listarTodos` |

| **POST** | `/api/usuarios` | `criarConta()` | `usuarioService.criarConta` |

| **GET** | `/api/usuarios/{id}` | `buscarPorId()` | `usuarioService.buscarPorId` |

| **POST** | `/api/usuarios/{id}/cartao` | `adicionarCartao()` | `usuarioService.adicionarCartao` |

| **GET** | `/api/usuarios/{usuarioId}/assinaturas` | `listar()` | `assinaturaService.listarPorUsuario` |

| **POST** | `/api/usuarios/{usuarioId}/assinaturas` | `contratar()` | `assinaturaService.contratarPlano` |

| **DELETE** | `/api/usuarios/{usuarioId}/assinaturas/{assinaturaId}` | `cancelar()` | `assinaturaService.cancelarPlano` |

| **GET** | `/api/usuarios/{usuarioId}/favoritos` | `buscarFavoritos()` | `bibliotecaService.buscarFavoritos` |

| **DELETE** | `/api/usuarios/{usuarioId}/favoritos/{musicaId}` | `desfavoritarMusica()` | `bibliotecaService.desfavoritarMusica` |

| **POST** | `/api/usuarios/{usuarioId}/favoritos/{musicaId}` | `favoritarMusica()` | `bibliotecaService.favoritarMusica` |

| **GET** | `/api/usuarios/{usuarioId}/playlists` | `listarPlaylists()` | `bibliotecaService.listarPlaylistsDoUsuario` |

| **POST** | `/api/usuarios/{usuarioId}/playlists` | `criarPlaylist()` | `bibliotecaService.criarPlaylist` |



### Detalhes de Cada Endpoint

#### `GET /api/musicas`

- **Implementação:** `List<MusicaResponse> listarTodas()`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/catalogo/api/MusicaController.java:40`

- **Cadeia de chamadas:**

  - `listarTodas()` → `List<Musica> listarTodas()` (CatalogoApplicationService.java:33)



#### `POST /api/musicas`

- **Implementação:** `MusicaResponse catalogar(@RequestBody @Valid CatalogarMusicaRequest request)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/catalogo/api/MusicaController.java:23`

- **Cadeia de chamadas:**

  - `catalogar()` → `Musica catalogarMusica(String titulo, String artista, String album, long duracaoSegundos, Genero genero)` (CatalogoApplicationService.java:21)



#### `GET /api/musicas/artista/{artista}`

- **Implementação:** `List<MusicaResponse> porArtista(@PathVariable String artista)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/catalogo/api/MusicaController.java:46`

- **Cadeia de chamadas:**

  - `porArtista()` → `List<Musica> buscarPorArtista(String artista)` (CatalogoApplicationService.java:38)



#### `GET /api/musicas/{id}`

- **Implementação:** `MusicaResponse buscarPorId(@PathVariable String id)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/catalogo/api/MusicaController.java:34`

- **Cadeia de chamadas:**

  - `buscarPorId()` → `Musica buscarPorId(String id)` (CatalogoApplicationService.java:27)



#### `DELETE /api/playlists/{playlistId}/musicas/{musicaId}`

- **Implementação:** `PlaylistResponse removerMusica(@PathVariable String playlistId, @PathVariable String musicaId)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/biblioteca/api/BibliotecaController.java:43`

- **Cadeia de chamadas:**

  - `removerMusica()` → `Playlist removerMusicaDaPlaylist(String playlistId, String musicaId)` (BibliotecaApplicationService.java:41)



#### `POST /api/playlists/{playlistId}/musicas/{musicaId}`

- **Implementação:** `PlaylistResponse adicionarMusica(@PathVariable String playlistId, @PathVariable String musicaId)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/biblioteca/api/BibliotecaController.java:37`

- **Cadeia de chamadas:**

  - `adicionarMusica()` → `Playlist adicionarMusicaNaPlaylist(String playlistId, String musicaId)` (BibliotecaApplicationService.java:32)



#### `POST /api/transacoes`

- **Implementação:** `TransacaoResponse autorizar(@RequestBody @Valid AutorizarTransacaoRequest request)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/pagamento/api/TransacaoController.java:24`

- **Cadeia de chamadas:**

  - `autorizar()` → `Transacao autorizarTransacao(String usuarioId, String comerciante, BigDecimal valor)` (AutorizacaoApplicationService.java:27)



#### `GET /api/transacoes/usuario/{usuarioId}`

- **Implementação:** `List<TransacaoResponse> listarPorUsuario(@PathVariable String usuarioId)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/pagamento/api/TransacaoController.java:34`

- **Cadeia de chamadas:**

  - `listarPorUsuario()` → `List<Transacao> listarTransacoesPorUsuario(String usuarioId)` (AutorizacaoApplicationService.java:38)



#### `GET /api/usuarios`

- **Implementação:** `List<UsuarioResponse> listarTodos()`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/identidade/api/UsuarioController.java:24`

- **Cadeia de chamadas:**

  - `listarTodos()` → `List<Usuario> listarTodos()` (UsuarioApplicationService.java:39)



#### `POST /api/usuarios`

- **Implementação:** `UsuarioResponse criarConta(@RequestBody @Valid CriarContaRequest request)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/identidade/api/UsuarioController.java:30`

- **Cadeia de chamadas:**

  - `criarConta()` → `Usuario criarConta(String nome, String email, String senha)` (UsuarioApplicationService.java:21)



#### `GET /api/usuarios/{id}`

- **Implementação:** `UsuarioResponse buscarPorId(@PathVariable String id)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/identidade/api/UsuarioController.java:46`

- **Cadeia de chamadas:**

  - `buscarPorId()` → `Usuario buscarPorId(String usuarioId)` (UsuarioApplicationService.java:34)



#### `POST /api/usuarios/{id}/cartao`

- **Implementação:** `UsuarioResponse adicionarCartao(@PathVariable String id, @RequestBody @Valid AdicionarCartaoRequest request)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/identidade/api/UsuarioController.java:38`

- **Cadeia de chamadas:**

  - `adicionarCartao()` → `Usuario adicionarCartao(String usuarioId, String ultimos4Digitos, String titular, YearMonth validade)` (UsuarioApplicationService.java:27)



#### `GET /api/usuarios/{usuarioId}/assinaturas`

- **Implementação:** `List<AssinaturaResponse> listar(@PathVariable String usuarioId)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/assinatura/api/AssinaturaController.java:32`

- **Cadeia de chamadas:**

  - `listar()` → `List<Assinatura> listarPorUsuario(String usuarioId)` (AssinaturaApplicationService.java:35)



#### `POST /api/usuarios/{usuarioId}/assinaturas`

- **Implementação:** `AssinaturaResponse contratar(@PathVariable String usuarioId, @RequestBody @Valid ContratarPlanoRequest request)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/assinatura/api/AssinaturaController.java:24`

- **Cadeia de chamadas:**

  - `contratar()` → `Assinatura contratarPlano(String usuarioId, TipoPlano tipoPlano, BigDecimal precoMensal)` (AssinaturaApplicationService.java:22)



#### `DELETE /api/usuarios/{usuarioId}/assinaturas/{assinaturaId}`

- **Implementação:** `AssinaturaResponse cancelar(@PathVariable String usuarioId, @PathVariable String assinaturaId)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/assinatura/api/AssinaturaController.java:38`

- **Cadeia de chamadas:**

  - `cancelar()` → `Assinatura cancelarPlano(String assinaturaId)` (AssinaturaApplicationService.java:28)



#### `GET /api/usuarios/{usuarioId}/favoritos`

- **Implementação:** `FavoritosResponse buscarFavoritos(@PathVariable String usuarioId)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/biblioteca/api/BibliotecaController.java:62`

- **Cadeia de chamadas:**

  - `buscarFavoritos()` → `Favoritos buscarFavoritos(String usuarioId)` (BibliotecaApplicationService.java:69)



#### `DELETE /api/usuarios/{usuarioId}/favoritos/{musicaId}`

- **Implementação:** `FavoritosResponse desfavoritarMusica(@PathVariable String usuarioId, @PathVariable String musicaId)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/biblioteca/api/BibliotecaController.java:56`

- **Cadeia de chamadas:**

  - `desfavoritarMusica()` → `Favoritos desfavoritarMusica(String usuarioId, String musicaId)` (BibliotecaApplicationService.java:62)



#### `POST /api/usuarios/{usuarioId}/favoritos/{musicaId}`

- **Implementação:** `FavoritosResponse favoritarMusica(@PathVariable String usuarioId, @PathVariable String musicaId)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/biblioteca/api/BibliotecaController.java:49`

- **Cadeia de chamadas:**

  - `favoritarMusica()` → `Favoritos favoritarMusica(String usuarioId, String musicaId)` (BibliotecaApplicationService.java:53)



#### `GET /api/usuarios/{usuarioId}/playlists`

- **Implementação:** `List<PlaylistResponse> listarPlaylists(@PathVariable String usuarioId)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/biblioteca/api/BibliotecaController.java:31`

- **Cadeia de chamadas:**

  - `listarPlaylists()` → `List<Playlist> listarPlaylistsDoUsuario(String usuarioId)` (BibliotecaApplicationService.java:48)



#### `POST /api/usuarios/{usuarioId}/playlists`

- **Implementação:** `PlaylistResponse criarPlaylist(@PathVariable String usuarioId, @RequestBody @Valid CriarPlaylistRequest request)`

- **Arquivo:** `src/main/java/EstevezAlvarez/StramingSong/biblioteca/api/BibliotecaController.java:23`

- **Cadeia de chamadas:**

  - `criarPlaylist()` → `Playlist criarPlaylist(String usuarioId, String nome)` (BibliotecaApplicationService.java:26)




## Classes

### AggregateRoot

- **Arquivo:** `AggregateRoot.java` (linha 5)

- **Tags:** —

- **Campos:** `domainEvents: List<DomainEvent>`

- **Métodos:** `registrarEvento()`, `domainEvents()`, `limparEventos()`



### AltaFrequenciaPequenoIntervaloRule

- **Arquivo:** `AltaFrequenciaPequenoIntervaloRule.java` (linha 9)

- **Anotações:** `@Component`

- **Tags:** `component`

- **Campos:** `limiteInferior: Instant`, `transacoesNaJanela: long`

- **Métodos:** `avaliar()`, `if()`



### Assinatura

- **Arquivo:** `Assinatura.java` (linha 12)

- **Anotações:** `@Entity` `@Table` `@NoArgsConstructor`

- **Tags:** `entity`, `lombok`

- **Campos:** `id: return`, `usuarioId: UUID`, `tipoPlano: TipoPlano`, `precoMensal: BigDecimal`, `status: StatusAssinatura`, `dataInicio: LocalDate`

- **Métodos:** `Assinatura()`, `contratar()`, `cancelar()`, `DomainException()`, `renovar()`, `estaAtiva()`, `id()`

- **Depende de:** TipoPlano, StatusAssinatura



### AssinaturaApplicationService

- **Arquivo:** `AssinaturaApplicationService.java` (linha 14)

- **Anotações:** `@Service` `@RequiredArgsConstructor`

- **Tags:** `service`, `lombok`

- **Campos:** `assinaturaRepository: AssinaturaRepository`, `servico: AssinaturaService`, `assinatura: Assinatura`

- **Métodos:** `contratarPlano()`, `cancelarPlano()`, `listarPorUsuario()`, `buscarOuLancarExcecao()`

- **Depende de:** AssinaturaRepository, AssinaturaService, Assinatura



### AssinaturaController

- **Arquivo:** `AssinaturaController.java` (linha 14)

- **Anotações:** `@RestController` `@RequestMapping` `@RequiredArgsConstructor` `@Tag`

- **Tags:** `controller`, `rest`, `lombok`

- **Campos:** `assinaturaService: AssinaturaApplicationService`, `assinatura: Assinatura`

- **Métodos:** `contratar()`, `listar()`, `cancelar()`, `ContratarPlanoRequest()`, `AssinaturaResponse()`, `de()`

- **Depende de:** AssinaturaApplicationService, Assinatura

- **Endpoints:** `POST /api/usuarios/{usuarioId}/assinaturas`, `GET /api/usuarios/{usuarioId}/assinaturas`, `DELETE /api/usuarios/{usuarioId}/assinaturas/{assinaturaId}`



### AssinaturaJpaRepository

- **Arquivo:** `AssinaturaJpaRepository.java` (linha 8)

- **Tags:** `interface`

- **Métodos:** `findByUsuarioId()`, `findByUsuarioIdAndStatus()`



### AssinaturaRepository

- **Arquivo:** `AssinaturaRepository.java` (linha 8)

- **Tags:** `interface`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarPorUsuario()`, `buscarAtivasPorUsuario()`



### AssinaturaRepositoryImpl

- **Arquivo:** `AssinaturaRepositoryImpl.java` (linha 11)

- **Anotações:** `@Repository` `@RequiredArgsConstructor`

- **Tags:** `repository`, `lombok`

- **Campos:** `jpa: AssinaturaJpaRepository`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarPorUsuario()`, `buscarAtivasPorUsuario()`

- **Depende de:** AssinaturaJpaRepository



### AssinaturaService

- **Arquivo:** `AssinaturaService.java` (linha 15)

- **Tags:** —

- **Campos:** `assinaturaRepository: AssinaturaRepository`, `id: UUID`, `ativas: List<Assinatura>`, `assinatura: Assinatura`

- **Métodos:** `AssinaturaService()`, `contratarPlano()`, `DomainException()`

- **Depende de:** AssinaturaRepository, Assinatura



### AutorizacaoApplicationService

- **Arquivo:** `AutorizacaoApplicationService.java` (linha 17)

- **Anotações:** `@Service` `@RequiredArgsConstructor`

- **Tags:** `service`, `lombok`

- **Campos:** `transacaoRepository: TransacaoRepository`, `usuarioRepository: UsuarioRepository`, `regrasAntifraude: List<RegraAntifraude>`, `usuario: Usuario`, `transacao: Transacao`, `servico: AutorizacaoTransacaoService`

- **Métodos:** `autorizarTransacao()`, `listarTransacoesPorUsuario()`

- **Depende de:** TransacaoRepository, UsuarioRepository, Usuario, Transacao, AutorizacaoTransacaoService



### AutorizacaoTransacaoService

- **Arquivo:** `AutorizacaoTransacaoService.java` (linha 17)

- **Tags:** —

- **Campos:** `transacaoRepository: TransacaoRepository`, `regrasAntifraude: List<RegraAntifraude>`, `limiteHistorico: Instant`, `historico: List<Transacao>`, `contexto: ContextoAvaliacao`, `violacoes: List<ViolacaoNegocio>`

- **Métodos:** `AutorizacaoTransacaoService()`, `autorizar()`

- **Depende de:** TransacaoRepository



### BcryptCriptografadorSenha

- **Arquivo:** `BcryptCriptografadorSenha.java` (linha 5)

- **Anotações:** `@Component`

- **Tags:** `component`

- **Campos:** `encoder: BCryptPasswordEncoder`

- **Métodos:** `criptografar()`, `confere()`



### BibliotecaApplicationService

- **Arquivo:** `BibliotecaApplicationService.java` (linha 16)

- **Anotações:** `@Service` `@RequiredArgsConstructor`

- **Tags:** `service`, `lombok`

- **Campos:** `playlistRepository: PlaylistRepository`, `favoritosRepository: FavoritosRepository`, `catalogoPort: CatalogoPort`, `playlist: Playlist`, `referencia: ReferenciaMusica`, `favoritos: Favoritos`

- **Métodos:** `criarPlaylist()`, `adicionarMusicaNaPlaylist()`, `removerMusicaDaPlaylist()`, `listarPlaylistsDoUsuario()`, `favoritarMusica()`, `desfavoritarMusica()`, `buscarFavoritos()`, `obterFavoritosOuLancarExcecao()`

- **Depende de:** PlaylistRepository, FavoritosRepository, CatalogoPort, Playlist, ReferenciaMusica, Favoritos



### BibliotecaController

- **Arquivo:** `BibliotecaController.java` (linha 14)

- **Anotações:** `@RestController` `@RequiredArgsConstructor` `@Tag`

- **Tags:** `controller`, `rest`, `lombok`

- **Campos:** `bibliotecaService: BibliotecaApplicationService`, `playlist: Playlist`, `items: List<MusicaItemResponse>`

- **Métodos:** `criarPlaylist()`, `listarPlaylists()`, `adicionarMusica()`, `removerMusica()`, `favoritarMusica()`, `desfavoritarMusica()`, `buscarFavoritos()`, `CriarPlaylistRequest()`

- **Depende de:** BibliotecaApplicationService, Playlist

- **Endpoints:** `POST /api/usuarios/{usuarioId}/playlists`, `GET /api/usuarios/{usuarioId}/playlists`, `POST /api/playlists/{playlistId}/musicas/{musicaId}`, `DELETE /api/playlists/{playlistId}/musicas/{musicaId}`, `POST /api/usuarios/{usuarioId}/favoritos/{musicaId}`, `DELETE /api/usuarios/{usuarioId}/favoritos/{musicaId}`, `GET /api/usuarios/{usuarioId}/favoritos`



### CadastroUsuarioService

- **Arquivo:** `CadastroUsuarioService.java` (linha 5)

- **Tags:** —

- **Campos:** `usuarioRepository: UsuarioRepository`, `criptografadorSenha: CriptografadorSenha`, `emailDominio: Email`, `usuario: Usuario`

- **Métodos:** `CadastroUsuarioService()`, `criarConta()`, `DomainException()`

- **Depende de:** UsuarioRepository, CriptografadorSenha, Email, Usuario



### CartaoCredito

- **Arquivo:** `CartaoCredito.java` (linha 9)

- **Anotações:** `@Embeddable`

- **Tags:** `entity`

- **Campos:** `ultimos4Digitos: return`, `titular: return`, `validade: return`, `status: return`

- **Métodos:** `CartaoCredito()`, `de()`, `DomainException()`, `bloquear()`, `estaAtivo()`, `ultimos4Digitos()`, `titular()`, `validade()`

- **Depende de:** StatusCartao



### CartaoInativoRule

- **Arquivo:** `CartaoInativoRule.java` (linha 7)

- **Anotações:** `@Component`

- **Tags:** `component`

- **Métodos:** `avaliar()`



### CatalogoAdapter

- **Arquivo:** `CatalogoAdapter.java` (linha 15)

- **Anotações:** `@Component` `@RequiredArgsConstructor`

- **Tags:** `component`, `lombok`

- **Campos:** `musicaRepository: MusicaRepository`

- **Métodos:** `buscarReferenciaMusica()`, `traduzirParaReferencia()`

- **Depende de:** MusicaRepository



### CatalogoApplicationService

- **Arquivo:** `CatalogoApplicationService.java` (linha 13)

- **Anotações:** `@Service` `@RequiredArgsConstructor`

- **Tags:** `service`, `lombok`

- **Campos:** `musicaRepository: MusicaRepository`, `musica: Musica`

- **Métodos:** `catalogarMusica()`, `buscarPorId()`, `listarTodas()`, `buscarPorArtista()`

- **Depende de:** MusicaRepository, Musica



### CatalogoPort

- **Arquivo:** `CatalogoPort.java` (linha 10)

- **Tags:** `interface`

- **Métodos:** `buscarReferenciaMusica()`



### CorsConfig

- **Arquivo:** `CorsConfig.java` (linha 5)

- **Anotações:** `@Configuration`

- **Tags:** `configuration`

- **Métodos:** `addCorsMappings()`



### CriptografadorSenha

- **Arquivo:** `CriptografadorSenha.java` (linha 1)

- **Tags:** `interface`

- **Métodos:** `criptografar()`, `confere()`



### DomainEvent

- **Arquivo:** `DomainEvent.java` (linha 3)

- **Tags:** `interface`

- **Métodos:** `ocorridoEm()`



### DomainException

- **Arquivo:** `DomainException.java` (linha 1)

- **Tags:** —

- **Métodos:** `DomainException()`, `super()`



### Email

- **Arquivo:** `Email.java` (linha 6)

- **Tags:** —

- **Campos:** `endereco: return`, `normalizado: String`

- **Métodos:** `Email()`, `de()`, `DomainException()`, `endereco()`, `equals()`, `hashCode()`, `toString()`



### Favoritos

- **Arquivo:** `Favoritos.java` (linha 13)

- **Anotações:** `@Entity` `@Table` `@NoArgsConstructor`

- **Tags:** `entity`, `lombok`

- **Campos:** `id: return`, `usuarioId: UUID`, `musicasFavoritas: List<ReferenciaMusica>`, `removida: boolean`

- **Métodos:** `Favoritos()`, `criarParaUsuario()`, `favoritarMusica()`, `desfavoritarMusica()`, `if()`, `DomainException()`, `musicasFavoritas()`, `id()`



### FavoritosJpaRepository

- **Arquivo:** `FavoritosJpaRepository.java` (linha 7)

- **Tags:** `interface`

- **Métodos:** `findByUsuarioId()`



### FavoritosRepository

- **Arquivo:** `FavoritosRepository.java` (linha 6)

- **Tags:** `interface`

- **Métodos:** `salvar()`, `buscarPorUsuario()`



### FavoritosRepositoryImpl

- **Arquivo:** `FavoritosRepositoryImpl.java` (linha 9)

- **Anotações:** `@Repository` `@RequiredArgsConstructor`

- **Tags:** `repository`, `lombok`

- **Campos:** `jpa: FavoritosJpaRepository`

- **Métodos:** `salvar()`, `buscarPorUsuario()`

- **Depende de:** FavoritosJpaRepository



### Genero

- **Arquivo:** `Genero.java` (linha 1)

- **Tags:** `enum`



### GlobalExceptionHandler

- **Arquivo:** `GlobalExceptionHandler.java` (linha 10)

- **Anotações:** `@RestControllerAdvice`

- **Tags:** —

- **Campos:** `problem: return`, `erros: String`

- **Métodos:** `handleDomainException()`, `handleValidation()`, `handleNotFound()`, `handleGeneric()`



### Musica

- **Arquivo:** `Musica.java` (linha 11)

- **Anotações:** `@Entity` `@Table` `@NoArgsConstructor`

- **Tags:** `entity`, `lombok`

- **Campos:** `id: UUID`, `titulo: String`, `artista: String`, `album: String`, `duracaoSegundos: long`, `genero: Genero`

- **Métodos:** `Musica()`, `catalogar()`, `validarTitulo()`, `validarArtista()`, `validarDuracao()`, `id()`, `duracao()`, `DomainException()`

- **Depende de:** Genero



### MusicaController

- **Arquivo:** `MusicaController.java` (linha 13)

- **Anotações:** `@RestController` `@RequestMapping` `@RequiredArgsConstructor` `@Tag`

- **Tags:** `controller`, `rest`, `lombok`

- **Campos:** `catalogoService: CatalogoApplicationService`, `musica: Musica`

- **Métodos:** `catalogar()`, `buscarPorId()`, `listarTodas()`, `porArtista()`, `CatalogarMusicaRequest()`, `MusicaResponse()`, `de()`

- **Depende de:** CatalogoApplicationService, Musica

- **Endpoints:** `POST /api/musicas`, `GET /api/musicas/{id}`, `GET /api/musicas`, `GET /api/musicas/artista/{artista}`



### MusicaId

- **Arquivo:** `MusicaId.java` (linha 6)

- **Tags:** —

- **Campos:** `valor: return`

- **Métodos:** `MusicaId()`, `novo()`, `de()`, `DomainException()`, `valor()`, `equals()`, `hashCode()`, `toString()`



### MusicaJpaRepository

- **Arquivo:** `MusicaJpaRepository.java` (linha 8)

- **Tags:** `interface`

- **Métodos:** `findByArtista()`, `findByGenero()`



### MusicaRepository

- **Arquivo:** `MusicaRepository.java` (linha 8)

- **Tags:** `interface`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarPorArtista()`, `buscarPorGenero()`, `listarTodas()`



### MusicaRepositoryImpl

- **Arquivo:** `MusicaRepositoryImpl.java` (linha 11)

- **Anotações:** `@Repository` `@RequiredArgsConstructor`

- **Tags:** `repository`, `lombok`

- **Campos:** `jpa: MusicaJpaRepository`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarPorArtista()`, `buscarPorGenero()`, `listarTodas()`

- **Depende de:** MusicaJpaRepository



### Playlist

- **Arquivo:** `Playlist.java` (linha 13)

- **Anotações:** `@Entity` `@Table` `@NoArgsConstructor`

- **Tags:** `entity`, `lombok`

- **Campos:** `id: UUID`, `usuarioId: UUID`, `nome: String`, `musicas: List<ReferenciaMusica>`, `removida: boolean`

- **Métodos:** `Playlist()`, `criar()`, `validarNome()`, `adicionarMusica()`, `removerMusica()`, `if()`, `DomainException()`, `renomear()`



### PlaylistId

- **Arquivo:** `PlaylistId.java` (linha 6)

- **Tags:** —

- **Campos:** `valor: return`

- **Métodos:** `PlaylistId()`, `novo()`, `de()`, `DomainException()`, `valor()`, `equals()`, `hashCode()`, `toString()`



### PlaylistJpaRepository

- **Arquivo:** `PlaylistJpaRepository.java` (linha 7)

- **Tags:** `interface`

- **Métodos:** `findByUsuarioId()`



### PlaylistRepository

- **Arquivo:** `PlaylistRepository.java` (linha 8)

- **Tags:** `interface`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarPorUsuario()`



### PlaylistRepositoryImpl

- **Arquivo:** `PlaylistRepositoryImpl.java` (linha 11)

- **Anotações:** `@Repository` `@RequiredArgsConstructor`

- **Tags:** `repository`, `lombok`

- **Campos:** `jpa: PlaylistJpaRepository`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarPorUsuario()`

- **Depende de:** PlaylistJpaRepository



### ReferenciaMusica

- **Arquivo:** `ReferenciaMusica.java` (linha 13)

- **Anotações:** `@Embeddable`

- **Tags:** `entity`

- **Campos:** `musicaId: UUID`, `titulo: return`, `artista: return`

- **Métodos:** `ReferenciaMusica()`, `de()`, `DomainException()`, `musicaId()`, `titulo()`, `artista()`, `equals()`, `hashCode()`



### RegraAntifraude

- **Arquivo:** `RegraAntifraude.java` (linha 12)

- **Tags:** `interface`

- **Métodos:** `avaliar()`



### Senha

- **Arquivo:** `Senha.java` (linha 5)

- **Tags:** —

- **Campos:** `hashBcrypt: return`

- **Métodos:** `Senha()`, `deTextoSimples()`, `DomainException()`, `deHash()`, `confere()`, `hashBcrypt()`, `equals()`, `hashCode()`



### StatusAssinatura

- **Arquivo:** `StatusAssinatura.java` (linha 1)

- **Tags:** `enum`



### StatusCartao

- **Arquivo:** `StatusCartao.java` (linha 1)

- **Tags:** `enum`



### StatusTransacao

- **Arquivo:** `StatusTransacao.java` (linha 1)

- **Tags:** `enum`



### StramingSongApplication

- **Arquivo:** `StramingSongApplication.java` (linha 4)

- **Anotações:** `@SpringBootApplication`

- **Tags:** `entrypoint`

- **Métodos:** `main()`



### StramingSongApplicationTests

- **Arquivo:** `StramingSongApplicationTests.java` (linha 4)

- **Anotações:** `@SpringBootTest`

- **Tags:** `test`

- **Métodos:** `contextLoads()`



### TipoPlano

- **Arquivo:** `TipoPlano.java` (linha 1)

- **Tags:** `enum`



### Transacao

- **Arquivo:** `Transacao.java` (linha 14)

- **Anotações:** `@Entity` `@Table` `@NoArgsConstructor`

- **Tags:** `entity`, `lombok`

- **Campos:** `id: return`, `usuarioId: UUID`, `comerciante: String`, `valor: BigDecimal`, `ocorridaEm: Instant`, `status: StatusTransacao`

- **Métodos:** `Transacao()`, `iniciar()`, `rejeitar()`, `foiAprovada()`, `id()`, `violacoes()`

- **Depende de:** StatusTransacao



### TransacaoController

- **Arquivo:** `TransacaoController.java` (linha 14)

- **Anotações:** `@RestController` `@RequestMapping` `@RequiredArgsConstructor` `@Tag`

- **Tags:** `controller`, `rest`, `lombok`

- **Campos:** `autorizacaoService: AutorizacaoApplicationService`, `transacao: Transacao`, `violacoes: List<ViolacaoResponse>`

- **Métodos:** `autorizar()`, `listarPorUsuario()`, `AutorizarTransacaoRequest()`, `TransacaoResponse()`, `de()`, `ViolacaoResponse()`

- **Depende de:** AutorizacaoApplicationService, Transacao

- **Endpoints:** `POST /api/transacoes`, `GET /api/transacoes/usuario/{usuarioId}`



### TransacaoDuplicadaRule

- **Arquivo:** `TransacaoDuplicadaRule.java` (linha 9)

- **Anotações:** `@Component`

- **Tags:** `component`

- **Campos:** `limiteInferior: Instant`, `transacoesSimilares: long`

- **Métodos:** `avaliar()`, `if()`



### TransacaoJpaRepository

- **Arquivo:** `TransacaoJpaRepository.java` (linha 8)

- **Tags:** `interface`

- **Métodos:** `findByUsuarioId()`, `findByUsuarioIdAndOcorridaEmAfter()`



### TransacaoRepository

- **Arquivo:** `TransacaoRepository.java` (linha 8)

- **Tags:** `interface`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarTransacoesAposInstante()`, `buscarPorUsuario()`



### TransacaoRepositoryImpl

- **Arquivo:** `TransacaoRepositoryImpl.java` (linha 11)

- **Anotações:** `@Repository` `@RequiredArgsConstructor`

- **Tags:** `repository`, `lombok`

- **Campos:** `jpa: TransacaoJpaRepository`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarTransacoesAposInstante()`, `buscarPorUsuario()`

- **Depende de:** TransacaoJpaRepository



### Usuario

- **Arquivo:** `Usuario.java` (linha 11)

- **Anotações:** `@Entity` `@Table` `@NoArgsConstructor`

- **Tags:** `entity`, `lombok`

- **Campos:** `id: UUID`, `nome: String`, `email: String`, `senhaHash: String`, `cartaoCredito: return`, `emailValido: Email`

- **Métodos:** `Usuario()`, `criar()`, `validarNome()`, `adicionarCartaoCredito()`, `bloquearCartao()`, `if()`, `DomainException()`, `possuiCartaoValido()`

- **Depende de:** CartaoCredito, Email, Senha



### UsuarioApplicationService

- **Arquivo:** `UsuarioApplicationService.java` (linha 12)

- **Anotações:** `@Service` `@RequiredArgsConstructor`

- **Tags:** `service`, `lombok`

- **Campos:** `usuarioRepository: UsuarioRepository`, `criptografadorSenha: CriptografadorSenha`, `servico: CadastroUsuarioService`, `usuario: Usuario`

- **Métodos:** `criarConta()`, `adicionarCartao()`, `buscarPorId()`, `listarTodos()`, `buscarOuLancarExcecao()`

- **Depende de:** UsuarioRepository, CriptografadorSenha, CadastroUsuarioService, Usuario



### UsuarioController

- **Arquivo:** `UsuarioController.java` (linha 14)

- **Anotações:** `@RestController` `@RequestMapping` `@RequiredArgsConstructor` `@Tag`

- **Tags:** `controller`, `rest`, `lombok`

- **Campos:** `usuarioService: UsuarioApplicationService`, `usuario: Usuario`, `validade: YearMonth`, `cartaoInfo: CartaoInfo`, `c: CartaoCredito`

- **Métodos:** `listarTodos()`, `criarConta()`, `adicionarCartao()`, `buscarPorId()`, `UsuarioResponse()`, `de()`, `CartaoInfo()`

- **Depende de:** UsuarioApplicationService, Usuario, CartaoCredito

- **Endpoints:** `GET /api/usuarios`, `POST /api/usuarios`, `POST /api/usuarios/{id}/cartao`, `GET /api/usuarios/{id}`



### UsuarioId

- **Arquivo:** `UsuarioId.java` (linha 6)

- **Tags:** —

- **Campos:** `valor: return`

- **Métodos:** `UsuarioId()`, `novo()`, `de()`, `DomainException()`, `valor()`, `equals()`, `hashCode()`, `toString()`



### UsuarioJpaRepository

- **Arquivo:** `UsuarioJpaRepository.java` (linha 7)

- **Tags:** `interface`

- **Métodos:** `findByEmail()`, `existsByEmail()`



### UsuarioRepository

- **Arquivo:** `UsuarioRepository.java` (linha 8)

- **Tags:** `interface`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarPorEmail()`, `existePorEmail()`, `listarTodos()`



### UsuarioRepositoryImpl

- **Arquivo:** `UsuarioRepositoryImpl.java` (linha 11)

- **Anotações:** `@Repository` `@RequiredArgsConstructor`

- **Tags:** `repository`, `lombok`

- **Campos:** `jpa: UsuarioJpaRepository`

- **Métodos:** `salvar()`, `buscarPorId()`, `buscarPorEmail()`, `existePorEmail()`, `listarTodos()`

- **Depende de:** UsuarioJpaRepository



### ViolacaoNegocio

- **Arquivo:** `ViolacaoNegocio.java` (linha 3)

- **Tags:** —

- **Campos:** `codigo: return`, `descricao: return`

- **Métodos:** `ViolacaoNegocio()`, `de()`, `codigo()`, `descricao()`, `equals()`, `hashCode()`, `toString()`



### ViolacaoNegocioJpa

- **Arquivo:** `ViolacaoNegocioJpa.java` (linha 6)

- **Anotações:** `@Embeddable` `@Getter` `@NoArgsConstructor` `@AllArgsConstructor`

- **Tags:** `entity`, `lombok`

- **Campos:** `codigo: String`, `descricao: String`



### YearMonthConverter

- **Arquivo:** `YearMonthConverter.java` (linha 6)

- **Anotações:** `@Converter`

- **Tags:** —

- **Métodos:** `convertToDatabaseColumn()`, `convertToEntityAttribute()`




## Guia para Agentes de IA

Este documento é gerado automaticamente pelo **Code Atlas**. Use-o junto com a ferramenta `atlas_get_context` para navegar no grafo.

### API de Contexto (para Agentes)


O servidor Code Atlas expõe endpoints de contexto estruturado:


| Endpoint | Descrição |

|----------|-----------|

| `GET /api/context/{node_id}` | ContextPackage completo de um nó (callers, callees, deps, endpoints) |

| `GET /api/node/{node_id}` | Dados do nó + arestas conectadas |

| `GET /api/search?q=nome` | Busca por label, tipo, tag ou arquivo |

| `GET /api/graph` | Grafo completo em formato Cytoscape JSON |

| `GET /api/docs/raw` | Este documento como JSON `{"markdown": ...}` |


> **Dica para IAs:** Para diagnosticar um bug em qualquer endpoint, consulte primeiro
> `GET /api/context/{method_node_id}` do método do Controller. O campo
> `structural_context.callees` mostra quais métodos do Service são chamados.
> Depois consulte o context do método do Service para ver seus `depends_on`
> (que apontam para Models/DTOs com possíveis erros de mapping).


### Como Rastrear uma Requisição

Para entender o fluxo completo de qualquer endpoint:
1. Encontre o endpoint na tabela acima
2. Localize o método Java correspondente no Controller
3. Siga as arestas `CALLS` para o(s) Service(s)
4. Verifique os DTOs de entrada/saída
5. O Model/Entity é o objeto central de domínio


### Onde Procurar por Tipo de Problema


| Problema | Onde Verificar |

|----------|----------------|

| Bug no retorno de dados | Service + método `toResponseDTO()` |

| Erro de validação (400) | Service + RequestDTO |

| Endpoint não encontrado (404) | Controller + `@RequestMapping` |

| Erro na estrutura do endpoint | Controller + `@GetMapping`/`@PostMapping` |

| Dados incorretos na resposta | Service + ResponseDTO |

| Problema de persistência | Repository + Model/Entity |


### Referências Rápidas


- **AssinaturaController** — `src/main/java/EstevezAlvarez/StramingSong/assinatura/api/AssinaturaController.java:14` — 6 métodos

- **BibliotecaController** — `src/main/java/EstevezAlvarez/StramingSong/biblioteca/api/BibliotecaController.java:14` — 12 métodos

- **MusicaController** — `src/main/java/EstevezAlvarez/StramingSong/catalogo/api/MusicaController.java:13` — 7 métodos

- **UsuarioController** — `src/main/java/EstevezAlvarez/StramingSong/identidade/api/UsuarioController.java:14` — 7 métodos

- **TransacaoController** — `src/main/java/EstevezAlvarez/StramingSong/pagamento/api/TransacaoController.java:14` — 6 métodos

- **AssinaturaApplicationService** — `src/main/java/EstevezAlvarez/StramingSong/assinatura/application/AssinaturaApplicationService.java:14` — 4 métodos

- **BibliotecaApplicationService** — `src/main/java/EstevezAlvarez/StramingSong/biblioteca/application/BibliotecaApplicationService.java:16` — 10 métodos

- **CatalogoApplicationService** — `src/main/java/EstevezAlvarez/StramingSong/catalogo/application/CatalogoApplicationService.java:13` — 4 métodos

- **UsuarioApplicationService** — `src/main/java/EstevezAlvarez/StramingSong/identidade/application/UsuarioApplicationService.java:12` — 5 métodos

- **AutorizacaoApplicationService** — `src/main/java/EstevezAlvarez/StramingSong/pagamento/application/AutorizacaoApplicationService.java:17` — 2 métodos


