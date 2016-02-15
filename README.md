### Disciplina - Técnicas de Programação II - CK0129 - Semestre 2015.2


[![Build Status](https://travis-ci.org/TPII20152/BankSys05.svg?branch=master)](https://travis-ci.org/TPII20152/BankSys05)

#### Setup: 
O líder deve colocar o código do BankSys sob controle de versão no GitHub. Certifique- se que todos os membros da sua equipe estão cadastrados como colaboradores do projeto/repositório. Em seguida, você e sua equipe devem distribuir as tarefas, descritas abaixo. Utilize o gerenciador de issues do GitHub para documentar as tarefas, atribuir responsáveis e acompanhar o andamento.
#### Tarefa 1
Construa casos de teste usando JUnit para cada uma das unidades (classes) do sistema BankSys. Desse modo, para cada unidade deve ser criado um Caso de Teste e para cada Cenário de Teste deve ser implementado em um método específico do Caso de Teste.
#### Tarefa 2
Faça a integração entre o projeto/repositório BankSys e o Travis. Coloque o BankSys sob Integração Continua (Travis) para a execução de Testes Automatizados e geração de Build.
#### Tarefa 3
New Feature – Implementar persistência no BankSys (em arquivo ou banco de dados). Para isso você deve implementar uma classe que estende IAccountRepository. Você pode usar a API padrão do Java para persistência em arquivo ou algum framework como o XStream (http://x-stream.github.io/).
#### Tarefa 4
New Feature – Implementar um log persistente de operações bancárias. Esse log deve registrar todas as operações bancárias (operações de debito, credito, bônus, juros, transferência, cadastro e remoção de contas) em um arquivo de log ou em um banco de dados.
#### Tarefa 5
New Feature – Implementar uma GUI com Java Swing para o BankSys (Terminal de Autoatendimento).

OBS. Não se esqueça de fazer os testes unitários para as novas funcionalidades (New Feature)! Caso bugs sejam detectados, eles devem ser fixados como issues do tipo Bug Fix.


### Git Workflow

**Step 1 Starting**
$ git checkout master
$ git pull

**Step 2 Creating a new work branch**
$ git checkout –b <work_branch>

**Step 3 Getting a updates from remote master**
$ git checkout master
$ git pull

**Step 4 Rebasing work branch**
$ git checkout <work_branch>
$ git rebase master

**Step 5 Merging**
$ git checkout master
$ git merge <work_branch>

**Step 6 Pushing**
$ git push origin master

teste
