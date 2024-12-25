#!/bin/bash

# Diretório a ser verificado
DIR="/caminho/do/seu/diretorio"

# Itera sobre os arquivos Java no diretório e subdiretórios
find "$DIR" -type f -name "*.java" | while read -r file; do
    # Verifica se o arquivo contém a palavra "Copyright" (case-insensitive)
    if ! grep -qi "Copyright" "$file"; then
        echo "Arquivo sem copyright: $file"
    fi
done
