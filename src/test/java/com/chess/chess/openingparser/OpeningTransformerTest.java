package com.chess.chess.openingparser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OpeningTransformerTest {
    String pgn = "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. e4 (8. Qd3  8... e5 9. dxe5 (9. d5 Nb4 10. Qd1 a5 ) 9... dxe5 10. Qxd8 Rxd8 11. Bg5 Re8 12. Rfd1 h6)  (8. e3  8... Rb8 9. b3 (9. Qe2 b5 10. Rd1 bxc4 11. Qxc4 Nb4 12. a3 Be6 ) 9... b5 10. cxb5 axb5 11. Bb2)  (8. h3   8... Bd7 9. e4 e5 10. d5 (10. Be3 exd4 11. Nxd4 Re8 12. Qd2 (12. Qc2 Ng4 ) 12... Ne5 13. b3 c5 14. Nde2 Bxh3 )  (10. dxe5  10... Nxe5 11. Nxe5 dxe5 12. Be3 Be6  13. Qe2  13... c6  14. Rfd1 ) 10... Nd4 11. Nxd4 exd4 12. Qxd4 Qc8  13. h4  (13. Kh2 Ng4+ )  (13. Bg5 Nh5  14. Qd2 Bxh3) 13... Ng4 14. Qd1 Ne5 15. Qe2 b5 16. cxb5 axb5 17. Nxb5 Qa6 18. Nc3 Qxe2 19. Nxe2 Bb5 20. Re1 Nd3 21. Rd1 Nxc1 )  (8. b3 Rb8  9. Bb2 (9. a4 e5 10. d5 Nd4 11. Nxd4 exd4 12. Qxd4 (12. Na2 c5 13. dxc6 bxc6  14. Bxc6 Qb6 15. Bg2 Qxb3) 12... Nxd5) 9... b5 10. cxb5 axb5 11. Rc1 (11. d5 Na5 12. Nd4 b4 13. Na4 (13. Ncb5 Nxd5 14. Bxd5 Rxb5 15. Nxb5 Bxb2) 13... e5 14. dxe6 fxe6) 11... b4 12. Na4 Na5 13. d5 (13. Qc2  13... c6  14. Ne1  14... Ba6  15. Nd3  15... Bb5 ) 13... Bd7 14. Nd4 e6 15. dxe6 fxe6 )  (8. d5 Na5 9. Nd2 c5 10. e4  (10. Rb1 Bf5 11. e4   11... Bd7  12. b3  (12. Qe2  12... e6 13. a3 exd5 14. cxd5 Qc7  ) 12... e5  13. Qc2 (13. Bb2 b5) 13... b5 14. cxb5 axb5)  (10. dxc6 Nxc6)  (10. a3  10... Qc7 )  (10. Qc2  10... b5 11. cxb5 axb5 12. Nxb5 Qb6 13. Nc3 Bf5 14. e4 Bc8 15. h3 Ba6 16. Rd1 Nd7)) 8... Bg4   9. h3 (9. Be3  9... Re8   10. h3 Bxf3 11. Bxf3 e5 12. d5 Nd4 13. Bxd4 (13. Bg2 c5 14. dxc6 bxc6) 13... exd4 14. Qxd4 Nxe4 ) 9... Bxf3 10. Bxf3 Nd7  11. Be3  (11. d5 Nd4 12. Bg2 c5) 11... e5 12. d5 Nd4 13. Bg2 (13. Bxd4 exd4 14. Ne2 Ne5  15. Nxd4  15... Nxc4) 13... c5 14. dxc6 bxc6";

    @Test
    void test() {
        List<String> transformed = new OpeningTransformer().transform("", pgn);
        System.out.println(transformed);
        Assertions.assertThat(transformed.size()).isEqualTo(28);
        Assertions.assertThat(transformed).contains(
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. e4 Bg4 9. h3 Bxf3 10. Bxf3 Nd7 11. Be3 e5 12. d5 Nd4 13. Bg2 c5 14. dxc6 bxc6 c5 14. dxc6 bxc6",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. e4 Bg4 9. h3 Bxf3 10. Bxf3 Nd7 11. Be3 e5 12. d5 Nd4 13. Bxd4 exd4 14. Ne2 Ne5 15. Nxd4 Nxc4",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. b3 Rb8 9. Bb2 b5 10. cxb5 axb5 11. d5 Na5 12. Nd4 b4 13. Na4 e5 14. dxe6 fxe6 e5 14. dxe6 fxe6",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. b3 Rb8 9. Bb2 b5 10. cxb5 axb5 11. d5 Na5 12. Nd4 b4 13. Ncb5 Nxd5 14. Bxd5 Rxb5 15. Nxb5 Bxb2",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. h3 Bd7 9. e4 e5 10. Be3 exd4 11. Nxd4 Re8 12. Qd2 Ne5 13. b3 c5 14. Nde2 Bxh3 Ne5 13. b3 c5 14. Nde2 Bxh3 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. d5 Na5 9. Nd2 c5 10. Rb1 Bf5 11. e4 Bd7 12. b3 e5 13. Qc2 b5 14. cxb5 axb5 b5 14. cxb5 axb5",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. d5 Na5 9. Nd2 c5 10. Rb1 Bf5 11. e4 Bd7 12. b3 e5 13. Bb2 b5",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. d5 Na5 9. Nd2 c5 10. a3 Qc7 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. h3 Bd7 9. e4 e5 10. d5 Nd4 11. Nxd4 exd4 12. Qxd4 Qc8 13. Bg5 Nh5 14. Qd2 Bxh3",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. e3 Rb8 9. b3 b5 10. cxb5 axb5 11. Bb2 b5 10. cxb5 axb5 11. Bb2",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. Qd3 e5 9. dxe5 dxe5 10. Qxd8 Rxd8 11. Bg5 Re8 12. Rfd1 h6 dxe5 10. Qxd8 Rxd8 11. Bg5 Re8 12. Rfd1 h6",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. d5 Na5 9. Nd2 c5 10. Rb1 Bf5 11. e4 Bd7 12. Qe2 e6 13. a3 exd5 14. cxd5 Qc7 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. d5 Na5 9. Nd2 c5 10. e4 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. d5 Na5 9. Nd2 c5 10. dxc6 Nxc6",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. b3 Rb8 9. Bb2 b5 10. cxb5 axb5 11. Rc1 b4 12. Na4 Na5 13. d5 Bd7 14. Nd4 e6 15. dxe6 fxe6 Bd7 14. Nd4 e6 15. dxe6 fxe6 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. h3 Bd7 9. e4 e5 10. Be3 exd4 11. Nxd4 Re8 12. Qc2 Ng4 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. b3 Rb8 9. a4 e5 10. d5 Nd4 11. Nxd4 exd4 12. Qxd4 Nxd5 Nxd5",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. h3 Bd7 9. e4 e5 10. d5 Nd4 11. Nxd4 exd4 12. Qxd4 Qc8 13. Kh2 Ng4+ ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. d5 Na5 9. Nd2 c5 10. Qc2 b5 11. cxb5 axb5 12. Nxb5 Qb6 13. Nc3 Bf5 14. e4 Bc8 15. h3 Ba6 16. Rd1 Nd7",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. e4 Bg4 9. Be3 Re8 10. h3 Bxf3 11. Bxf3 e5 12. d5 Nd4 13. Bg2 c5 14. dxc6 bxc6",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. b3 Rb8 9. Bb2 b5 10. cxb5 axb5 11. Rc1 b4 12. Na4 Na5 13. Qc2 c6 14. Ne1 Ba6 15. Nd3 Bb5 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. h3 Bd7 9. e4 e5 10. dxe5 Nxe5 11. Nxe5 dxe5 12. Be3 Be6 13. Qe2 c6 14. Rfd1 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. e3 Rb8 9. Qe2 b5 10. Rd1 bxc4 11. Qxc4 Nb4 12. a3 Be6 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. b3 Rb8 9. a4 e5 10. d5 Nd4 11. Nxd4 exd4 12. Na2 c5 13. dxc6 bxc6 14. Bxc6 Qb6 15. Bg2 Qxb3",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. Qd3 e5 9. d5 Nb4 10. Qd1 a5 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. h3 Bd7 9. e4 e5 10. d5 Nd4 11. Nxd4 exd4 12. Qxd4 Qc8 13. h4 Ng4 14. Qd1 Ne5 15. Qe2 b5 16. cxb5 axb5 17. Nxb5 Qa6 18. Nc3 Qxe2 19. Nxe2 Bb5 20. Re1 Nd3 21. Rd1 Nxc1 Ng4 14. Qd1 Ne5 15. Qe2 b5 16. cxb5 axb5 17. Nxb5 Qa6 18. Nc3 Qxe2 19. Nxe2 Bb5 20. Re1 Nd3 21. Rd1 Nxc1 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. e4 Bg4 9. Be3 Re8 10. h3 Bxf3 11. Bxf3 e5 12. d5 Nd4 13. Bxd4 exd4 14. Qxd4 Nxe4 exd4 14. Qxd4 Nxe4 ",
                "1. d4 Nf6 2. c4 g6 3. Nf3 Bg7 4. g3 O-O 5. Bg2 d6 6. O-O Nc6 7. Nc3 a6 8. e4 Bg4 9. h3 Bxf3 10. Bxf3 Nd7 11. d5 Nd4 12. Bg2 c5"
        );

    }
}
