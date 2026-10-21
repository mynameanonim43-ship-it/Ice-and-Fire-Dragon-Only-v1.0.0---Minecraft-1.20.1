@echo off
setlocal
echo ====================================================
echo   Push Mod "Ice and Fire: Dragon Only" ke GitHub
echo ====================================================
echo.

set GIT="C:\Program Files\Git\cmd\git.exe"

set REPO_URL=%1
if "%REPO_URL%"=="" (
    set /p REPO_URL="Masukkan URL repository GitHub Anda (contoh: https://github.com/username/ice-and-fire-dragon-only.git): "
)

if "%REPO_URL%"=="" (
    echo [ERROR] URL repository tidak boleh kosong!
    pause
    exit /b 1
)

echo.
echo [1/3] Menghubungkan remote origin ke: %REPO_URL%
%GIT% remote remove origin >nul 2>&1
%GIT% remote add origin %REPO_URL%

echo [2/3] Memastikan branch utama adalah 'main'...
%GIT% branch -M main

echo [3/3] Melakukan push kode ke GitHub...
%GIT% push -u origin main

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ====================================================
    echo   [SUKSES] Source code berhasil di-push ke GitHub!
    echo ====================================================
) else (
    echo.
    echo [INFO] Jika diminta login, silakan login melalui popup browser GitHub yang muncul.
)

echo.
pause
