@echo off
setlocal enabledelayedexpansion

rem Diretório a ser verificado
set "DIR=C:\caminho\do\seu\diretorio"

rem Itera sobre os arquivos Java no diretório e subdiretórios
for /r "%DIR%" %%F in (*.java) do (
    set "found="
    rem Verifica se o arquivo contém a palavra "Copyright" (case-insensitive)
    for /f "usebackq delims=" %%L in ("%%F") do (
        set "line=%%L"
        setlocal enabledelayedexpansion
        if /i "!line:Copyright=!" neq "!line!" (
            set "found=1"
            endlocal
            goto :nextFile
        )
        endlocal
    )
    if not defined found (
        echo Arquivo sem copyright: %%F
    )
    :nextFile
)
