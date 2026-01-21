# LZW Archiver

A Java implementation of the LZW (Lempel-Ziv-Welch) compression algorithm with variable-width code encoding.

## Description

This project provides a command-line tool for compressing and decompressing files using the LZW algorithm. The implementation uses variable-width code encoding, which automatically adjusts the code size (starting from 9 bits) as the dictionary grows, providing efficient compression for various file types.

## Features

- **LZW Compression**: Implements the classic LZW algorithm for lossless data compression
- **Variable-Width Coding**: Automatically adjusts code width (9-16 bits) for optimal compression
- **Command-Line Interface**: Simple and intuitive CLI for compression and extraction
- **Cross-Platform**: Pure Java implementation, runs on any platform with Java support

## Requirements

- Java 8 or higher
- No external dependencies

## Building

Compile the project using `javac`:

```bash
javac -d build src/com/tomclaw/lzw/*.java
```

Or use your preferred Java build tool.

## Usage

The program accepts three command-line arguments:

```
bzz <command> <source> <destination>
```

### Commands

- `-c` - Create (compress) a file
- `-x` - Extract (decompress) an archive
- `-h` - Show help message

### Examples

**Compress a file:**
```bash
java -cp build com.tomclaw.lzw.Main -c input.txt output.bzz
```

**Extract an archive:**
```bash
java -cp build com.tomclaw.lzw.Main -x archive.bzz output.txt
```

**Show help:**
```bash
java -cp build com.tomclaw.lzw.Main -h
```

### Command Details

#### Create Archive (`-c`)
- `<source>` - Path to the file to compress
- `<destination>` - Path for the output archive (typically with `.bzz` extension)

#### Extract Archive (`-x`)
- `<source>` - Path to the archive file (typically with `.bzz` extension)
- `<destination>` - Path for the extracted file

## How It Works

1. **Compression**: The algorithm builds a dictionary of byte sequences as it processes the input. When a sequence is found in the dictionary, it outputs the corresponding code. New sequences are added to the dictionary dynamically.

2. **Variable-Width Coding**: Codes are written using a variable number of bits:
   - Codes 256-511: 9 bits
   - Codes 512-1023: 10 bits
   - Codes 1024-2047: 11 bits
   - And so on, up to 16 bits maximum

3. **Decompression**: The decoder rebuilds the dictionary using the same algorithm, ensuring perfect reconstruction of the original data.

## Error Handling

The program provides clear error messages for common issues:
- Invalid number of arguments
- File not found
- Invalid command
- I/O errors

Exit codes:
- `0` - Success
- `1` - General error
- `2` - Usage error

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

Igor Solkin (2020)

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
