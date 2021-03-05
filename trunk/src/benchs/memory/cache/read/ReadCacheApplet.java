package benchs.memory.cache.read;

/*
 * Copyright (c) 2006 Mesure Project
 * 
 * This software is a computer program whose purpose is to measure 
 * the performances of Java Card platforms.
 *
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info". 
 * 
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 * 
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 * 
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

import benchs.lib.templates.TemplateApplet;
import benchs.lib.templates.TestCase;

public class ReadCacheApplet extends TemplateApplet {

    
    public static short[] SHORT_REF = {(short) 0, (short) 33, (short) 34, (short) 35, (short) 36, (short) 37, (short) 38, 
				       (short) 39, (short) 40, (short) 41, (short) 42, (short) 43, (short) 44, (short) 45, 
				       (short) 46, (short) 47, (short) 48, (short) 49, (short) 50, (short) 51, (short) 52, 
				       (short) 53, (short) 54, (short) 55, (short) 56, (short) 57, (short) 58, (short) 59, 
				       (short) 60, (short) 61, (short) 62, (short) 63, (short) 1, (short) 2, (short) 3, 
				       (short) 4, (short) 5, (short) 6, (short) 7, (short) 8, (short) 9, (short) 10, 
				       (short) 11, (short) 12, (short) 13, (short) 14, (short) 15, (short) 16, (short) 17, 
				       (short) 18, (short) 19, (short) 20, (short) 21, (short) 22, (short) 23, (short) 24, 
				       (short) 25, (short) 26, (short) 27, (short) 28, (short) 29, (short) 30, (short) 31, 
				       (short) 0, (short) 64, (short) 65, (short) 66, 
				       (short) 67, (short) 68, (short) 69, (short) 70, (short) 71, (short) 72, (short) 73, 
				       (short) 74, (short) 75, (short) 76, (short) 77, (short) 78, (short) 79, (short) 80, 
				       (short) 81, (short) 82, (short) 83, (short) 84, (short) 85, (short) 86, (short) 87, 
				       (short) 88, (short) 89, (short) 90, (short) 91, (short) 92, (short) 93, (short) 94, 
				       (short) 95, (short) 96, (short) 97, (short) 98, (short) 99, (short) 100, (short) 101, 
				       (short) 102, (short) 103, (short) 104, (short) 105, (short) 106, (short) 107, (short) 108, 
				       (short) 109, (short) 110, (short) 111, (short) 112, (short) 113, (short) 114, (short) 115, 
				       (short) 116, (short) 117, (short) 118, (short) 119, (short) 120, (short) 121, (short) 122, 
				       (short) 123, (short) 124, (short) 125, (short) 126, (short) 127, (short) 128, (short) 129, 
				       (short) 130, (short) 131, (short) 132, (short) 133, (short) 134, (short) 135, (short) 136, 
				       (short) 137, (short) 138, (short) 139, (short) 140, (short) 141, (short) 142, (short) 143, 
				       (short) 144, (short) 145, (short) 146, (short) 147, (short) 148, (short) 149, (short) 150, 
				       (short) 151, (short) 152, (short) 153, (short) 154, (short) 155, (short) 156, (short) 157, 
				       (short) 158, (short) 159, (short) 160, (short) 161, (short) 162, (short) 163, (short) 164, 
				       (short) 165, (short) 166, (short) 167, (short) 168, (short) 169, (short) 170, (short) 171, 
				       (short) 172, (short) 173, (short) 174, (short) 175, (short) 176, (short) 177, (short) 178, 
				       (short) 179, (short) 180, (short) 181, (short) 182, (short) 183, (short) 184, (short) 185, 
				       (short) 186, (short) 187, (short) 188, (short) 189, (short) 190, (short) 191, (short) 192, 
				       (short) 193, (short) 194, (short) 195, (short) 196, (short) 197, (short) 198, (short) 199, 
				       (short) 200, (short) 201, (short) 202, (short) 203, (short) 204, (short) 205, (short) 206, 
				       (short) 207, (short) 208, (short) 209, (short) 210, (short) 211, (short) 212, (short) 213, 
				       (short) 214, (short) 215, (short) 216, (short) 217, (short) 218, (short) 219, (short) 220, 
				       (short) 221, (short) 222, (short) 223, (short) 224, (short) 225, (short) 226, (short) 227, 
				       (short) 228, (short) 229, (short) 230, (short) 231, (short) 232, (short) 233, (short) 234, 
				       (short) 235, (short) 236, (short) 237, (short) 238, (short) 239, (short) 240, (short) 241, 
				       (short) 242, (short) 243, (short) 244, (short) 245, (short) 246, (short) 247, (short) 248, 
				       (short) 249, (short) 250, (short) 251, (short) 252, (short) 253, (short) 254, (short) 255, 
				       (short) 256, (short) 257, (short) 258, (short) 259, (short) 260, (short) 261, (short) 262, 
				       (short) 263, (short) 264, (short) 265, (short) 266, (short) 267, (short) 268, (short) 269, 
				       (short) 270, (short) 271, (short) 272, (short) 273, (short) 274, (short) 275, (short) 276, 
				       (short) 277, (short) 278, (short) 279, (short) 280, (short) 281, (short) 282, (short) 283, 
				       (short) 284, (short) 285, (short) 286, (short) 287, (short) 288, (short) 289, (short) 290, 
				       (short) 291, (short) 292, (short) 293, (short) 294, (short) 295, (short) 296, (short) 297, 
				       (short) 298, (short) 299, (short) 300, (short) 301, (short) 302, (short) 303, (short) 304, 
				       (short) 305, (short) 306, (short) 307, (short) 308, (short) 309, (short) 310, (short) 311, 
				       (short) 312, (short) 313, (short) 314, (short) 315, (short) 316, (short) 317, (short) 318, 
				       (short) 319, (short) 320, (short) 321, (short) 322, (short) 323, (short) 324, (short) 325, 
				       (short) 326, (short) 327, (short) 328, (short) 329, (short) 330, (short) 331, (short) 332, 
				       (short) 333, (short) 334, (short) 335, (short) 336, (short) 337, (short) 338, (short) 339, 
				       (short) 340, (short) 341, (short) 342, (short) 343, (short) 344, (short) 345, (short) 346, 
				       (short) 347, (short) 348, (short) 349, (short) 350, (short) 351, (short) 352, (short) 353, 
				       (short) 354, (short) 355, (short) 356, (short) 357, (short) 358, (short) 359, (short) 360, 
				       (short) 361, (short) 362, (short) 363, (short) 364, (short) 365, (short) 366, (short) 367, 
				       (short) 368, (short) 369, (short) 370, (short) 371, (short) 372, (short) 373, (short) 374, 
				       (short) 375, (short) 376, (short) 377, (short) 378, (short) 379, (short) 380, (short) 381, 
				       (short) 382, (short) 383, (short) 384, (short) 385, (short) 386, (short) 387, (short) 388, 
				       (short) 389, (short) 390, (short) 391, (short) 392, (short) 393, (short) 394, (short) 395, 
				       (short) 396, (short) 397, (short) 398, (short) 399, (short) 400, (short) 401, (short) 402, 
				       (short) 403, (short) 404, (short) 405, (short) 406, (short) 407, (short) 408, (short) 409, 
				       (short) 410, (short) 411, (short) 412, (short) 413, (short) 414, (short) 415, (short) 416, 
				       (short) 417, (short) 418, (short) 419, (short) 420, (short) 421, (short) 422, (short) 423, 
				       (short) 424, (short) 425, (short) 426, (short) 427, (short) 428, (short) 429, (short) 430, 
				       (short) 431, (short) 432, (short) 433, (short) 434, (short) 435, (short) 436, (short) 437, 
				       (short) 438, (short) 439, (short) 440, (short) 441, (short) 442, (short) 443, (short) 444, 
				       (short) 445, (short) 446, (short) 447, (short) 448, (short) 449, (short) 450, (short) 451, 
				       (short) 452, (short) 453, (short) 454, (short) 455, (short) 456, (short) 457, (short) 458, 
				       (short) 459, (short) 460, (short) 461, (short) 462, (short) 463, (short) 464, (short) 465, 
				       (short) 466, (short) 467, (short) 468, (short) 469, (short) 470, (short) 471, (short) 472, 
				       (short) 473, (short) 474, (short) 475, (short) 476, (short) 477, (short) 478, (short) 479, 
				       (short) 480, (short) 481, (short) 482, (short) 483, (short) 484, (short) 485, (short) 486, 
				       (short) 487, (short) 488, (short) 489, (short) 490, (short) 491, (short) 492, (short) 493, 
				       (short) 494, (short) 495, (short) 496, (short) 497, (short) 498, (short) 499, (short) 500, 
				       (short) 501, (short) 502, (short) 503, (short) 504, (short) 505, (short) 506, (short) 507, 
				       (short) 508, (short) 509, (short) 510, (short) 511};


    public static short[] SHORT_CACHE_2 = {(short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7, 
					   (short) 8, (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14, 
					   (short) 15, (short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21, 
					   (short) 22, (short) 23, (short) 24, (short) 25, (short) 26, (short) 27, (short) 28, 
					   (short) 29, (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35, 
					   (short) 36, (short) 37, (short) 38, (short) 39, (short) 40, (short) 41, (short) 42, 
					   (short) 43, (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49, 
					   (short) 50, (short) 51, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56, 
					   (short) 57, (short) 58, (short) 59, (short) 60, (short) 61, (short) 62, (short) 63, 
					   (short) 64, (short) 65, (short) 66, (short) 67, (short) 68, (short) 69, (short) 70, 
					   (short) 71, (short) 72, (short) 73, (short) 74, (short) 75, (short) 76, (short) 77, 
					   (short) 78, (short) 79, (short) 80, (short) 81, (short) 82, (short) 83, (short) 84, 
					   (short) 85, (short) 86, (short) 87, (short) 88, (short) 89, (short) 90, (short) 91, 
					   (short) 92, (short) 93, (short) 94, (short) 95, (short) 96, (short) 97, (short) 98, 
					   (short) 99, (short) 100, (short) 101, (short) 102, (short) 103, (short) 104, (short) 105, 
					   (short) 106, (short) 107, (short) 108, (short) 109, (short) 110, (short) 111, (short) 112, 
					   (short) 113, (short) 114, (short) 115, (short) 116, (short) 117, (short) 118, (short) 119, 
					   (short) 120, (short) 121, (short) 122, (short) 123, (short) 124, (short) 125, (short) 126, 
					   (short) 127, (short) 128, (short) 129, (short) 130, (short) 131, (short) 132, (short) 133, 
					   (short) 134, (short) 135, (short) 136, (short) 137, (short) 138, (short) 139, (short) 140, 
					   (short) 141, (short) 142, (short) 143, (short) 144, (short) 145, (short) 146, (short) 147, 
					   (short) 148, (short) 149, (short) 150, (short) 151, (short) 152, (short) 153, (short) 154, 
					   (short) 155, (short) 156, (short) 157, (short) 158, (short) 159, (short) 160, (short) 161, 
					   (short) 162, (short) 163, (short) 164, (short) 165, (short) 166, (short) 167, (short) 168, 
					   (short) 169, (short) 170, (short) 171, (short) 172, (short) 173, (short) 174, (short) 175, 
					   (short) 176, (short) 177, (short) 178, (short) 179, (short) 180, (short) 181, (short) 182, 
					   (short) 183, (short) 184, (short) 185, (short) 186, (short) 187, (short) 188, (short) 189, 
					   (short) 190, (short) 191, (short) 192, (short) 193, (short) 194, (short) 195, (short) 196, 
					   (short) 197, (short) 198, (short) 199, (short) 200, (short) 201, (short) 202, (short) 203, 
					   (short) 204, (short) 205, (short) 206, (short) 207, (short) 208, (short) 209, (short) 210, 
					   (short) 211, (short) 212, (short) 213, (short) 214, (short) 215, (short) 216, (short) 217, 
					   (short) 218, (short) 219, (short) 220, (short) 221, (short) 222, (short) 223, (short) 224, 
					   (short) 225, (short) 226, (short) 227, (short) 228, (short) 229, (short) 230, (short) 231, 
					   (short) 232, (short) 233, (short) 234, (short) 235, (short) 236, (short) 237, (short) 238, 
					   (short) 239, (short) 240, (short) 241, (short) 242, (short) 243, (short) 244, (short) 245, 
					   (short) 246, (short) 247, (short) 248, (short) 249, (short) 250, (short) 251, (short) 252, 
					   (short) 253, (short) 254, (short) 255, (short) 256, (short) 257, (short) 258, (short) 259, 
					   (short) 260, (short) 261, (short) 262, (short) 263, (short) 264, (short) 265, (short) 266, 
					   (short) 267, (short) 268, (short) 269, (short) 270, (short) 271, (short) 272, (short) 273, 
					   (short) 274, (short) 275, (short) 276, (short) 277, (short) 278, (short) 279, (short) 280, 
					   (short) 281, (short) 282, (short) 283, (short) 284, (short) 285, (short) 286, (short) 287, 
					   (short) 288, (short) 289, (short) 290, (short) 291, (short) 292, (short) 293, (short) 294, 
					   (short) 295, (short) 296, (short) 297, (short) 298, (short) 299, (short) 300, (short) 301, 
					   (short) 302, (short) 303, (short) 304, (short) 305, (short) 306, (short) 307, (short) 308, 
					   (short) 309, (short) 310, (short) 311, (short) 312, (short) 313, (short) 314, (short) 315, 
					   (short) 316, (short) 317, (short) 318, (short) 319, (short) 320, (short) 321, (short) 322, 
					   (short) 323, (short) 324, (short) 325, (short) 326, (short) 327, (short) 328, (short) 329, 
					   (short) 330, (short) 331, (short) 332, (short) 333, (short) 334, (short) 335, (short) 336, 
					   (short) 337, (short) 338, (short) 339, (short) 340, (short) 341, (short) 342, (short) 343, 
					   (short) 344, (short) 345, (short) 346, (short) 347, (short) 348, (short) 349, (short) 350, 
					   (short) 351, (short) 352, (short) 353, (short) 354, (short) 355, (short) 356, (short) 357, 
					   (short) 358, (short) 359, (short) 360, (short) 361, (short) 362, (short) 363, (short) 364, 
					   (short) 365, (short) 366, (short) 367, (short) 368, (short) 369, (short) 370, (short) 371, 
					   (short) 372, (short) 373, (short) 374, (short) 375, (short) 376, (short) 377, (short) 378, 
					   (short) 379, (short) 380, (short) 381, (short) 382, (short) 383, (short) 384, (short) 385, 
					   (short) 386, (short) 387, (short) 388, (short) 389, (short) 390, (short) 391, (short) 392, 
					   (short) 393, (short) 394, (short) 395, (short) 396, (short) 397, (short) 398, (short) 399, 
					   (short) 400, (short) 401, (short) 402, (short) 403, (short) 404, (short) 405, (short) 406, 
					   (short) 407, (short) 408, (short) 409, (short) 410, (short) 411, (short) 412, (short) 413, 
					   (short) 414, (short) 415, (short) 416, (short) 417, (short) 418, (short) 419, (short) 420, 
					   (short) 421, (short) 422, (short) 423, (short) 424, (short) 425, (short) 426, (short) 427, 
					   (short) 428, (short) 429, (short) 430, (short) 431, (short) 432, (short) 433, (short) 434, 
					   (short) 435, (short) 436, (short) 437, (short) 438, (short) 439, (short) 440, (short) 441, 
					   (short) 442, (short) 443, (short) 444, (short) 445, (short) 446, (short) 447, (short) 448, 
					   (short) 449, (short) 450, (short) 451, (short) 452, (short) 453, (short) 454, (short) 455, 
					   (short) 456, (short) 457, (short) 458, (short) 459, (short) 460, (short) 461, (short) 462, 
					   (short) 463, (short) 464, (short) 465, (short) 466, (short) 467, (short) 468, (short) 469, 
					   (short) 470, (short) 471, (short) 472, (short) 473, (short) 474, (short) 475, (short) 476, 
					   (short) 477, (short) 478, (short) 479, (short) 480, (short) 481, (short) 482, (short) 483, 
					   (short) 484, (short) 485, (short) 486, (short) 487, (short) 488, (short) 489, (short) 490, 
					   (short) 491, (short) 492, (short) 493, (short) 494, (short) 495, (short) 496, (short) 497, 
					   (short) 498, (short) 499, (short) 500, (short) 501, (short) 502, (short) 503, (short) 504, 
					   (short) 505, (short) 506, (short) 507, (short) 508, (short) 509, (short) 510, (short) 511, 
					   (short) 0};

    public static short[] SHORT_CACHE_4 = {(short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7, (short) 8, 
					   (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14, (short) 15, 
					   (short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21, (short) 22, 
					   (short) 23, (short) 24, (short) 25, (short) 26, (short) 27, (short) 28, (short) 29, 
					   (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35, (short) 36, 
					   (short) 37, (short) 38, (short) 39, (short) 40, (short) 41, (short) 42, (short) 43, 
					   (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49, (short) 50, 
					   (short) 51, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56, (short) 57, 
					   (short) 58, (short) 59, (short) 60, (short) 61, (short) 62, (short) 63, (short) 64, 
					   (short) 65, (short) 66, (short) 67, (short) 68, (short) 69, (short) 70, (short) 71, 
					   (short) 72, (short) 73, (short) 74, (short) 75, (short) 76, (short) 77, (short) 78, 
					   (short) 79, (short) 80, (short) 81, (short) 82, (short) 83, (short) 84, (short) 85, 
					   (short) 86, (short) 87, (short) 88, (short) 89, (short) 90, (short) 91, (short) 92, 
					   (short) 93, (short) 94, (short) 95, (short) 96, (short) 97, (short) 98, (short) 99, 
					   (short) 100, (short) 101, (short) 102, (short) 103, (short) 104, (short) 105, (short) 106, 
					   (short) 107, (short) 108, (short) 109, (short) 110, (short) 111, (short) 112, (short) 113, 
					   (short) 114, (short) 115, (short) 116, (short) 117, (short) 118, (short) 119, (short) 120, 
					   (short) 121, (short) 122, (short) 123, (short) 124, (short) 125, (short) 126, (short) 127, 
					   (short) 128, (short) 129, (short) 130, (short) 131, (short) 132, (short) 133, (short) 134, 
					   (short) 135, (short) 136, (short) 137, (short) 138, (short) 139, (short) 140, (short) 141, 
					   (short) 142, (short) 143, (short) 144, (short) 145, (short) 146, (short) 147, (short) 148, 
					   (short) 149, (short) 150, (short) 151, (short) 152, (short) 153, (short) 154, (short) 155, 
					   (short) 156, (short) 157, (short) 158, (short) 159, (short) 160, (short) 161, (short) 162, 
					   (short) 163, (short) 164, (short) 165, (short) 166, (short) 167, (short) 168, (short) 169, 
					   (short) 170, (short) 171, (short) 172, (short) 173, (short) 174, (short) 175, (short) 176, 
					   (short) 177, (short) 178, (short) 179, (short) 180, (short) 181, (short) 182, (short) 183, 
					   (short) 184, (short) 185, (short) 186, (short) 187, (short) 188, (short) 189, (short) 190, 
					   (short) 191, (short) 192, (short) 193, (short) 194, (short) 195, (short) 196, (short) 197, 
					   (short) 198, (short) 199, (short) 200, (short) 201, (short) 202, (short) 203, (short) 204, 
					   (short) 205, (short) 206, (short) 207, (short) 208, (short) 209, (short) 210, (short) 211, 
					   (short) 212, (short) 213, (short) 214, (short) 215, (short) 216, (short) 217, (short) 218, 
					   (short) 219, (short) 220, (short) 221, (short) 222, (short) 223, (short) 224, (short) 225, 
					   (short) 226, (short) 227, (short) 228, (short) 229, (short) 230, (short) 231, (short) 232, 
					   (short) 233, (short) 234, (short) 235, (short) 236, (short) 237, (short) 238, (short) 239, 
					   (short) 240, (short) 241, (short) 242, (short) 243, (short) 244, (short) 245, (short) 246, 
					   (short) 247, (short) 248, (short) 249, (short) 250, (short) 251, (short) 252, (short) 253, 
					   (short) 254, (short) 255, (short) 256, (short) 257, (short) 258, (short) 259, (short) 260, 
					   (short) 261, (short) 262, (short) 263, (short) 264, (short) 265, (short) 266, (short) 267, 
					   (short) 268, (short) 269, (short) 270, (short) 271, (short) 272, (short) 273, (short) 274, 
					   (short) 275, (short) 276, (short) 277, (short) 278, (short) 279, (short) 280, (short) 281, 
					   (short) 282, (short) 283, (short) 284, (short) 285, (short) 286, (short) 287, (short) 288, 
					   (short) 289, (short) 290, (short) 291, (short) 292, (short) 293, (short) 294, (short) 295, 
					   (short) 296, (short) 297, (short) 298, (short) 299, (short) 300, (short) 301, (short) 302, 
					   (short) 303, (short) 304, (short) 305, (short) 306, (short) 307, (short) 308, (short) 309, 
					   (short) 310, (short) 311, (short) 312, (short) 313, (short) 314, (short) 315, (short) 316, 
					   (short) 317, (short) 318, (short) 319, (short) 320, (short) 321, (short) 322, (short) 323, 
					   (short) 324, (short) 325, (short) 326, (short) 327, (short) 328, (short) 329, (short) 330, 
					   (short) 331, (short) 332, (short) 333, (short) 334, (short) 335, (short) 336, (short) 337, 
					   (short) 338, (short) 339, (short) 340, (short) 341, (short) 342, (short) 343, (short) 344, 
					   (short) 345, (short) 346, (short) 347, (short) 348, (short) 349, (short) 350, (short) 351, 
					   (short) 352, (short) 353, (short) 354, (short) 355, (short) 356, (short) 357, (short) 358, 
					   (short) 359, (short) 360, (short) 361, (short) 362, (short) 363, (short) 364, (short) 365, 
					   (short) 366, (short) 367, (short) 368, (short) 369, (short) 370, (short) 371, (short) 372, 
					   (short) 373, (short) 374, (short) 375, (short) 376, (short) 377, (short) 378, (short) 379, 
					   (short) 380, (short) 381, (short) 382, (short) 383, (short) 384, (short) 385, (short) 386, 
					   (short) 387, (short) 388, (short) 389, (short) 390, (short) 391, (short) 392, (short) 393, 
					   (short) 394, (short) 395, (short) 396, (short) 397, (short) 398, (short) 399, (short) 400, 
					   (short) 401, (short) 402, (short) 403, (short) 404, (short) 405, (short) 406, (short) 407, 
					   (short) 408, (short) 409, (short) 410, (short) 411, (short) 412, (short) 413, (short) 414, 
					   (short) 415, (short) 416, (short) 417, (short) 418, (short) 419, (short) 420, (short) 421, 
					   (short) 422, (short) 423, (short) 424, (short) 425, (short) 426, (short) 427, (short) 428, 
					   (short) 429, (short) 430, (short) 431, (short) 432, (short) 433, (short) 434, (short) 435, 
					   (short) 436, (short) 437, (short) 438, (short) 439, (short) 440, (short) 441, (short) 442, 
					   (short) 443, (short) 444, (short) 445, (short) 446, (short) 447, (short) 448, (short) 449, 
					   (short) 450, (short) 451, (short) 452, (short) 453, (short) 454, (short) 455, (short) 456, 
					   (short) 457, (short) 458, (short) 459, (short) 460, (short) 461, (short) 462, (short) 463, 
					   (short) 464, (short) 465, (short) 466, (short) 467, (short) 468, (short) 469, (short) 470, 
					   (short) 471, (short) 472, (short) 473, (short) 474, (short) 475, (short) 476, (short) 477, 
					   (short) 478, (short) 479, (short) 480, (short) 481, (short) 482, (short) 483, (short) 484, 
					   (short) 485, (short) 486, (short) 487, (short) 488, (short) 489, (short) 490, (short) 491, 
					   (short) 492, (short) 493, (short) 494, (short) 495, (short) 496, (short) 497, (short) 498, 
					   (short) 499, (short) 500, (short) 501, (short) 502, (short) 503, (short) 504, (short) 505, 
					   (short) 506, (short) 507, (short) 508, (short) 509, (short) 510, (short) 511, (short) 1, 
					   (short) 0};

    public static short[] SHORT_CACHE_8 = {(short) 4, (short) 5, (short) 6, (short) 7, (short) 8, (short) 9, (short) 10, 
					   (short) 11, (short) 12, (short) 13, (short) 14, (short) 15, (short) 16, (short) 17, 
					   (short) 18, (short) 19, (short) 20, (short) 21, (short) 22, (short) 23, (short) 24, 
					   (short) 25, (short) 26, (short) 27, (short) 28, (short) 29, (short) 30, (short) 31, 
					   (short) 32, (short) 33, (short) 34, (short) 35, (short) 36, (short) 37, (short) 38, 
					   (short) 39, (short) 40, (short) 41, (short) 42, (short) 43, (short) 44, (short) 45, 
					   (short) 46, (short) 47, (short) 48, (short) 49, (short) 50, (short) 51, (short) 52, 
					   (short) 53, (short) 54, (short) 55, (short) 56, (short) 57, (short) 58, (short) 59, 
					   (short) 60, (short) 61, (short) 62, (short) 63, (short) 64, (short) 65, (short) 66, 
					   (short) 67, (short) 68, (short) 69, (short) 70, (short) 71, (short) 72, (short) 73, 
					   (short) 74, (short) 75, (short) 76, (short) 77, (short) 78, (short) 79, (short) 80, 
					   (short) 81, (short) 82, (short) 83, (short) 84, (short) 85, (short) 86, (short) 87, 
					   (short) 88, (short) 89, (short) 90, (short) 91, (short) 92, (short) 93, (short) 94, 
					   (short) 95, (short) 96, (short) 97, (short) 98, (short) 99, (short) 100, (short) 101, 
					   (short) 102, (short) 103, (short) 104, (short) 105, (short) 106, (short) 107, (short) 108, 
					   (short) 109, (short) 110, (short) 111, (short) 112, (short) 113, (short) 114, (short) 115, 
					   (short) 116, (short) 117, (short) 118, (short) 119, (short) 120, (short) 121, (short) 122, 
					   (short) 123, (short) 124, (short) 125, (short) 126, (short) 127, (short) 128, (short) 129, 
					   (short) 130, (short) 131, (short) 132, (short) 133, (short) 134, (short) 135, (short) 136, 
					   (short) 137, (short) 138, (short) 139, (short) 140, (short) 141, (short) 142, (short) 143, 
					   (short) 144, (short) 145, (short) 146, (short) 147, (short) 148, (short) 149, (short) 150, 
					   (short) 151, (short) 152, (short) 153, (short) 154, (short) 155, (short) 156, (short) 157, 
					   (short) 158, (short) 159, (short) 160, (short) 161, (short) 162, (short) 163, (short) 164, 
					   (short) 165, (short) 166, (short) 167, (short) 168, (short) 169, (short) 170, (short) 171, 
					   (short) 172, (short) 173, (short) 174, (short) 175, (short) 176, (short) 177, (short) 178, 
					   (short) 179, (short) 180, (short) 181, (short) 182, (short) 183, (short) 184, (short) 185, 
					   (short) 186, (short) 187, (short) 188, (short) 189, (short) 190, (short) 191, (short) 192, 
					   (short) 193, (short) 194, (short) 195, (short) 196, (short) 197, (short) 198, (short) 199, 
					   (short) 200, (short) 201, (short) 202, (short) 203, (short) 204, (short) 205, (short) 206, 
					   (short) 207, (short) 208, (short) 209, (short) 210, (short) 211, (short) 212, (short) 213, 
					   (short) 214, (short) 215, (short) 216, (short) 217, (short) 218, (short) 219, (short) 220, 
					   (short) 221, (short) 222, (short) 223, (short) 224, (short) 225, (short) 226, (short) 227, 
					   (short) 228, (short) 229, (short) 230, (short) 231, (short) 232, (short) 233, (short) 234, 
					   (short) 235, (short) 236, (short) 237, (short) 238, (short) 239, (short) 240, (short) 241, 
					   (short) 242, (short) 243, (short) 244, (short) 245, (short) 246, (short) 247, (short) 248, 
					   (short) 249, (short) 250, (short) 251, (short) 252, (short) 253, (short) 254, (short) 255, 
					   (short) 256, (short) 257, (short) 258, (short) 259, (short) 260, (short) 261, (short) 262, 
					   (short) 263, (short) 264, (short) 265, (short) 266, (short) 267, (short) 268, (short) 269, 
					   (short) 270, (short) 271, (short) 272, (short) 273, (short) 274, (short) 275, (short) 276, 
					   (short) 277, (short) 278, (short) 279, (short) 280, (short) 281, (short) 282, (short) 283, 
					   (short) 284, (short) 285, (short) 286, (short) 287, (short) 288, (short) 289, (short) 290, 
					   (short) 291, (short) 292, (short) 293, (short) 294, (short) 295, (short) 296, (short) 297, 
					   (short) 298, (short) 299, (short) 300, (short) 301, (short) 302, (short) 303, (short) 304, 
					   (short) 305, (short) 306, (short) 307, (short) 308, (short) 309, (short) 310, (short) 311, 
					   (short) 312, (short) 313, (short) 314, (short) 315, (short) 316, (short) 317, (short) 318, 
					   (short) 319, (short) 320, (short) 321, (short) 322, (short) 323, (short) 324, (short) 325, 
					   (short) 326, (short) 327, (short) 328, (short) 329, (short) 330, (short) 331, (short) 332, 
					   (short) 333, (short) 334, (short) 335, (short) 336, (short) 337, (short) 338, (short) 339, 
					   (short) 340, (short) 341, (short) 342, (short) 343, (short) 344, (short) 345, (short) 346, 
					   (short) 347, (short) 348, (short) 349, (short) 350, (short) 351, (short) 352, (short) 353, 
					   (short) 354, (short) 355, (short) 356, (short) 357, (short) 358, (short) 359, (short) 360, 
					   (short) 361, (short) 362, (short) 363, (short) 364, (short) 365, (short) 366, (short) 367, 
					   (short) 368, (short) 369, (short) 370, (short) 371, (short) 372, (short) 373, (short) 374, 
					   (short) 375, (short) 376, (short) 377, (short) 378, (short) 379, (short) 380, (short) 381, 
					   (short) 382, (short) 383, (short) 384, (short) 385, (short) 386, (short) 387, (short) 388, 
					   (short) 389, (short) 390, (short) 391, (short) 392, (short) 393, (short) 394, (short) 395, 
					   (short) 396, (short) 397, (short) 398, (short) 399, (short) 400, (short) 401, (short) 402, 
					   (short) 403, (short) 404, (short) 405, (short) 406, (short) 407, (short) 408, (short) 409, 
					   (short) 410, (short) 411, (short) 412, (short) 413, (short) 414, (short) 415, (short) 416, 
					   (short) 417, (short) 418, (short) 419, (short) 420, (short) 421, (short) 422, (short) 423, 
					   (short) 424, (short) 425, (short) 426, (short) 427, (short) 428, (short) 429, (short) 430, 
					   (short) 431, (short) 432, (short) 433, (short) 434, (short) 435, (short) 436, (short) 437, 
					   (short) 438, (short) 439, (short) 440, (short) 441, (short) 442, (short) 443, (short) 444, 
					   (short) 445, (short) 446, (short) 447, (short) 448, (short) 449, (short) 450, (short) 451, 
					   (short) 452, (short) 453, (short) 454, (short) 455, (short) 456, (short) 457, (short) 458, 
					   (short) 459, (short) 460, (short) 461, (short) 462, (short) 463, (short) 464, (short) 465, 
					   (short) 466, (short) 467, (short) 468, (short) 469, (short) 470, (short) 471, (short) 472, 
					   (short) 473, (short) 474, (short) 475, (short) 476, (short) 477, (short) 478, (short) 479, 
					   (short) 480, (short) 481, (short) 482, (short) 483, (short) 484, (short) 485, (short) 486, 
					   (short) 487, (short) 488, (short) 489, (short) 490, (short) 491, (short) 492, (short) 493, 
					   (short) 494, (short) 495, (short) 496, (short) 497, (short) 498, (short) 499, (short) 500, 
					   (short) 501, (short) 502, (short) 503, (short) 504, (short) 505, (short) 506, (short) 507, 
					   (short) 508, (short) 509, (short) 510, (short) 511, (short) 1, (short) 2, (short) 3, 
					   (short) 0};


    public static short[] SHORT_CACHE_16 = {(short) 8, (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14, 
					    (short) 15, (short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21, 
					    (short) 22, (short) 23, (short) 24, (short) 25, (short) 26, (short) 27, (short) 28, 
					    (short) 29, (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35, 
					    (short) 36, (short) 37, (short) 38, (short) 39, (short) 40, (short) 41, (short) 42, 
					    (short) 43, (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49, 
					    (short) 50, (short) 51, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56, 
					    (short) 57, (short) 58, (short) 59, (short) 60, (short) 61, (short) 62, (short) 63, 
					    (short) 64, (short) 65, (short) 66, (short) 67, (short) 68, (short) 69, (short) 70, 
					    (short) 71, (short) 72, (short) 73, (short) 74, (short) 75, (short) 76, (short) 77, 
					    (short) 78, (short) 79, (short) 80, (short) 81, (short) 82, (short) 83, (short) 84, 
					    (short) 85, (short) 86, (short) 87, (short) 88, (short) 89, (short) 90, (short) 91, 
					    (short) 92, (short) 93, (short) 94, (short) 95, (short) 96, (short) 97, (short) 98, 
					    (short) 99, (short) 100, (short) 101, (short) 102, (short) 103, (short) 104, (short) 105, 
					    (short) 106, (short) 107, (short) 108, (short) 109, (short) 110, (short) 111, (short) 112, 
					    (short) 113, (short) 114, (short) 115, (short) 116, (short) 117, (short) 118, (short) 119, 
					    (short) 120, (short) 121, (short) 122, (short) 123, (short) 124, (short) 125, (short) 126, 
					    (short) 127, (short) 128, (short) 129, (short) 130, (short) 131, (short) 132, (short) 133, 
					    (short) 134, (short) 135, (short) 136, (short) 137, (short) 138, (short) 139, (short) 140, 
					    (short) 141, (short) 142, (short) 143, (short) 144, (short) 145, (short) 146, (short) 147, 
					    (short) 148, (short) 149, (short) 150, (short) 151, (short) 152, (short) 153, (short) 154, 
					    (short) 155, (short) 156, (short) 157, (short) 158, (short) 159, (short) 160, (short) 161, 
					    (short) 162, (short) 163, (short) 164, (short) 165, (short) 166, (short) 167, (short) 168, 
					    (short) 169, (short) 170, (short) 171, (short) 172, (short) 173, (short) 174, (short) 175, 
					    (short) 176, (short) 177, (short) 178, (short) 179, (short) 180, (short) 181, (short) 182, 
					    (short) 183, (short) 184, (short) 185, (short) 186, (short) 187, (short) 188, (short) 189, 
					    (short) 190, (short) 191, (short) 192, (short) 193, (short) 194, (short) 195, (short) 196, 
					    (short) 197, (short) 198, (short) 199, (short) 200, (short) 201, (short) 202, (short) 203, 
					    (short) 204, (short) 205, (short) 206, (short) 207, (short) 208, (short) 209, (short) 210, 
					    (short) 211, (short) 212, (short) 213, (short) 214, (short) 215, (short) 216, (short) 217, 
					    (short) 218, (short) 219, (short) 220, (short) 221, (short) 222, (short) 223, (short) 224, 
					    (short) 225, (short) 226, (short) 227, (short) 228, (short) 229, (short) 230, (short) 231, 
					    (short) 232, (short) 233, (short) 234, (short) 235, (short) 236, (short) 237, (short) 238, 
					    (short) 239, (short) 240, (short) 241, (short) 242, (short) 243, (short) 244, (short) 245, 
					    (short) 246, (short) 247, (short) 248, (short) 249, (short) 250, (short) 251, (short) 252, 
					    (short) 253, (short) 254, (short) 255, (short) 256, (short) 257, (short) 258, (short) 259, 
					    (short) 260, (short) 261, (short) 262, (short) 263, (short) 264, (short) 265, (short) 266, 
					    (short) 267, (short) 268, (short) 269, (short) 270, (short) 271, (short) 272, (short) 273, 
					    (short) 274, (short) 275, (short) 276, (short) 277, (short) 278, (short) 279, (short) 280, 
					    (short) 281, (short) 282, (short) 283, (short) 284, (short) 285, (short) 286, (short) 287, 
					    (short) 288, (short) 289, (short) 290, (short) 291, (short) 292, (short) 293, (short) 294, 
					    (short) 295, (short) 296, (short) 297, (short) 298, (short) 299, (short) 300, (short) 301, 
					    (short) 302, (short) 303, (short) 304, (short) 305, (short) 306, (short) 307, (short) 308, 
					    (short) 309, (short) 310, (short) 311, (short) 312, (short) 313, (short) 314, (short) 315, 
					    (short) 316, (short) 317, (short) 318, (short) 319, (short) 320, (short) 321, (short) 322, 
					    (short) 323, (short) 324, (short) 325, (short) 326, (short) 327, (short) 328, (short) 329, 
					    (short) 330, (short) 331, (short) 332, (short) 333, (short) 334, (short) 335, (short) 336, 
					    (short) 337, (short) 338, (short) 339, (short) 340, (short) 341, (short) 342, (short) 343, 
					    (short) 344, (short) 345, (short) 346, (short) 347, (short) 348, (short) 349, (short) 350, 
					    (short) 351, (short) 352, (short) 353, (short) 354, (short) 355, (short) 356, (short) 357, 
					    (short) 358, (short) 359, (short) 360, (short) 361, (short) 362, (short) 363, (short) 364, 
					    (short) 365, (short) 366, (short) 367, (short) 368, (short) 369, (short) 370, (short) 371, 
					    (short) 372, (short) 373, (short) 374, (short) 375, (short) 376, (short) 377, (short) 378, 
					    (short) 379, (short) 380, (short) 381, (short) 382, (short) 383, (short) 384, (short) 385, 
					    (short) 386, (short) 387, (short) 388, (short) 389, (short) 390, (short) 391, (short) 392, 
					    (short) 393, (short) 394, (short) 395, (short) 396, (short) 397, (short) 398, (short) 399, 
					    (short) 400, (short) 401, (short) 402, (short) 403, (short) 404, (short) 405, (short) 406, 
					    (short) 407, (short) 408, (short) 409, (short) 410, (short) 411, (short) 412, (short) 413, 
					    (short) 414, (short) 415, (short) 416, (short) 417, (short) 418, (short) 419, (short) 420, 
					    (short) 421, (short) 422, (short) 423, (short) 424, (short) 425, (short) 426, (short) 427, 
					    (short) 428, (short) 429, (short) 430, (short) 431, (short) 432, (short) 433, (short) 434, 
					    (short) 435, (short) 436, (short) 437, (short) 438, (short) 439, (short) 440, (short) 441, 
					    (short) 442, (short) 443, (short) 444, (short) 445, (short) 446, (short) 447, (short) 448, 
					    (short) 449, (short) 450, (short) 451, (short) 452, (short) 453, (short) 454, (short) 455, 
					    (short) 456, (short) 457, (short) 458, (short) 459, (short) 460, (short) 461, (short) 462, 
					    (short) 463, (short) 464, (short) 465, (short) 466, (short) 467, (short) 468, (short) 469, 
					    (short) 470, (short) 471, (short) 472, (short) 473, (short) 474, (short) 475, (short) 476, 
					    (short) 477, (short) 478, (short) 479, (short) 480, (short) 481, (short) 482, (short) 483, 
					    (short) 484, (short) 485, (short) 486, (short) 487, (short) 488, (short) 489, (short) 490, 
					    (short) 491, (short) 492, (short) 493, (short) 494, (short) 495, (short) 496, (short) 497, 
					    (short) 498, (short) 499, (short) 500, (short) 501, (short) 502, (short) 503, (short) 504, 
					    (short) 505, (short) 506, (short) 507, (short) 508, (short) 509, (short) 510, (short) 511, 
					    (short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7, 
					    (short) 0};

    public static short[] SHORT_CACHE_32 = {(short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21, (short) 22, 
					    (short) 23, (short) 24, (short) 25, (short) 26, (short) 27, (short) 28, (short) 29, 
					    (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35, (short) 36, 
					    (short) 37, (short) 38, (short) 39, (short) 40, (short) 41, (short) 42, (short) 43, 
					    (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49, (short) 50, 
					    (short) 51, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56, (short) 57, 
					    (short) 58, (short) 59, (short) 60, (short) 61, (short) 62, (short) 63, (short) 64, 
					    (short) 65, (short) 66, (short) 67, (short) 68, (short) 69, (short) 70, (short) 71, 
					    (short) 72, (short) 73, (short) 74, (short) 75, (short) 76, (short) 77, (short) 78, 
					    (short) 79, (short) 80, (short) 81, (short) 82, (short) 83, (short) 84, (short) 85, 
					    (short) 86, (short) 87, (short) 88, (short) 89, (short) 90, (short) 91, (short) 92, 
					    (short) 93, (short) 94, (short) 95, (short) 96, (short) 97, (short) 98, (short) 99, 
					    (short) 100, (short) 101, (short) 102, (short) 103, (short) 104, (short) 105, (short) 106, 
					    (short) 107, (short) 108, (short) 109, (short) 110, (short) 111, (short) 112, (short) 113, 
					    (short) 114, (short) 115, (short) 116, (short) 117, (short) 118, (short) 119, (short) 120, 
					    (short) 121, (short) 122, (short) 123, (short) 124, (short) 125, (short) 126, (short) 127, 
					    (short) 128, (short) 129, (short) 130, (short) 131, (short) 132, (short) 133, (short) 134, 
					    (short) 135, (short) 136, (short) 137, (short) 138, (short) 139, (short) 140, (short) 141, 
					    (short) 142, (short) 143, (short) 144, (short) 145, (short) 146, (short) 147, (short) 148, 
					    (short) 149, (short) 150, (short) 151, (short) 152, (short) 153, (short) 154, (short) 155, 
					    (short) 156, (short) 157, (short) 158, (short) 159, (short) 160, (short) 161, (short) 162, 
					    (short) 163, (short) 164, (short) 165, (short) 166, (short) 167, (short) 168, (short) 169, 
					    (short) 170, (short) 171, (short) 172, (short) 173, (short) 174, (short) 175, (short) 176, 
					    (short) 177, (short) 178, (short) 179, (short) 180, (short) 181, (short) 182, (short) 183, 
					    (short) 184, (short) 185, (short) 186, (short) 187, (short) 188, (short) 189, (short) 190, 
					    (short) 191, (short) 192, (short) 193, (short) 194, (short) 195, (short) 196, (short) 197, 
					    (short) 198, (short) 199, (short) 200, (short) 201, (short) 202, (short) 203, (short) 204, 
					    (short) 205, (short) 206, (short) 207, (short) 208, (short) 209, (short) 210, (short) 211, 
					    (short) 212, (short) 213, (short) 214, (short) 215, (short) 216, (short) 217, (short) 218, 
					    (short) 219, (short) 220, (short) 221, (short) 222, (short) 223, (short) 224, (short) 225, 
					    (short) 226, (short) 227, (short) 228, (short) 229, (short) 230, (short) 231, (short) 232, 
					    (short) 233, (short) 234, (short) 235, (short) 236, (short) 237, (short) 238, (short) 239, 
					    (short) 240, (short) 241, (short) 242, (short) 243, (short) 244, (short) 245, (short) 246, 
					    (short) 247, (short) 248, (short) 249, (short) 250, (short) 251, (short) 252, (short) 253, 
					    (short) 254, (short) 255, (short) 256, (short) 257, (short) 258, (short) 259, (short) 260, 
					    (short) 261, (short) 262, (short) 263, (short) 264, (short) 265, (short) 266, (short) 267, 
					    (short) 268, (short) 269, (short) 270, (short) 271, (short) 272, (short) 273, (short) 274, 
					    (short) 275, (short) 276, (short) 277, (short) 278, (short) 279, (short) 280, (short) 281, 
					    (short) 282, (short) 283, (short) 284, (short) 285, (short) 286, (short) 287, (short) 288, 
					    (short) 289, (short) 290, (short) 291, (short) 292, (short) 293, (short) 294, (short) 295, 
					    (short) 296, (short) 297, (short) 298, (short) 299, (short) 300, (short) 301, (short) 302, 
					    (short) 303, (short) 304, (short) 305, (short) 306, (short) 307, (short) 308, (short) 309, 
					    (short) 310, (short) 311, (short) 312, (short) 313, (short) 314, (short) 315, (short) 316, 
					    (short) 317, (short) 318, (short) 319, (short) 320, (short) 321, (short) 322, (short) 323, 
					    (short) 324, (short) 325, (short) 326, (short) 327, (short) 328, (short) 329, (short) 330, 
					    (short) 331, (short) 332, (short) 333, (short) 334, (short) 335, (short) 336, (short) 337, 
					    (short) 338, (short) 339, (short) 340, (short) 341, (short) 342, (short) 343, (short) 344, 
					    (short) 345, (short) 346, (short) 347, (short) 348, (short) 349, (short) 350, (short) 351, 
					    (short) 352, (short) 353, (short) 354, (short) 355, (short) 356, (short) 357, (short) 358, 
					    (short) 359, (short) 360, (short) 361, (short) 362, (short) 363, (short) 364, (short) 365, 
					    (short) 366, (short) 367, (short) 368, (short) 369, (short) 370, (short) 371, (short) 372, 
					    (short) 373, (short) 374, (short) 375, (short) 376, (short) 377, (short) 378, (short) 379, 
					    (short) 380, (short) 381, (short) 382, (short) 383, (short) 384, (short) 385, (short) 386, 
					    (short) 387, (short) 388, (short) 389, (short) 390, (short) 391, (short) 392, (short) 393, 
					    (short) 394, (short) 395, (short) 396, (short) 397, (short) 398, (short) 399, (short) 400, 
					    (short) 401, (short) 402, (short) 403, (short) 404, (short) 405, (short) 406, (short) 407, 
					    (short) 408, (short) 409, (short) 410, (short) 411, (short) 412, (short) 413, (short) 414, 
					    (short) 415, (short) 416, (short) 417, (short) 418, (short) 419, (short) 420, (short) 421, 
					    (short) 422, (short) 423, (short) 424, (short) 425, (short) 426, (short) 427, (short) 428, 
					    (short) 429, (short) 430, (short) 431, (short) 432, (short) 433, (short) 434, (short) 435, 
					    (short) 436, (short) 437, (short) 438, (short) 439, (short) 440, (short) 441, (short) 442, 
					    (short) 443, (short) 444, (short) 445, (short) 446, (short) 447, (short) 448, (short) 449, 
					    (short) 450, (short) 451, (short) 452, (short) 453, (short) 454, (short) 455, (short) 456, 
					    (short) 457, (short) 458, (short) 459, (short) 460, (short) 461, (short) 462, (short) 463, 
					    (short) 464, (short) 465, (short) 466, (short) 467, (short) 468, (short) 469, (short) 470, 
					    (short) 471, (short) 472, (short) 473, (short) 474, (short) 475, (short) 476, (short) 477, 
					    (short) 478, (short) 479, (short) 480, (short) 481, (short) 482, (short) 483, (short) 484, 
					    (short) 485, (short) 486, (short) 487, (short) 488, (short) 489, (short) 490, (short) 491, 
					    (short) 492, (short) 493, (short) 494, (short) 495, (short) 496, (short) 497, (short) 498, 
					    (short) 499, (short) 500, (short) 501, (short) 502, (short) 503, (short) 504, (short) 505, 
					    (short) 506, (short) 507, (short) 508, (short) 509, (short) 510, (short) 511, (short) 1, 
					    (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7, (short) 8, 
					    (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14, (short) 15, 
					    (short) 0};


    public static short[] SHORT_CACHE_64 = {(short) 32, (short) 33, (short) 34, (short) 35, (short) 36, (short) 37, (short) 38, 
					    (short) 39, (short) 40, (short) 41, (short) 42, (short) 43, (short) 44, (short) 45, 
					    (short) 46, (short) 47, (short) 48, (short) 49, (short) 50, (short) 51, (short) 52, 
					    (short) 53, (short) 54, (short) 55, (short) 56, (short) 57, (short) 58, (short) 59, 
					    (short) 60, (short) 61, (short) 62, (short) 63, (short) 64, (short) 65, (short) 66, 
					    (short) 67, (short) 68, (short) 69, (short) 70, (short) 71, (short) 72, (short) 73, 
					    (short) 74, (short) 75, (short) 76, (short) 77, (short) 78, (short) 79, (short) 80, 
					    (short) 81, (short) 82, (short) 83, (short) 84, (short) 85, (short) 86, (short) 87, 
					    (short) 88, (short) 89, (short) 90, (short) 91, (short) 92, (short) 93, (short) 94, 
					    (short) 95, (short) 96, (short) 97, (short) 98, (short) 99, (short) 100, (short) 101, 
					    (short) 102, (short) 103, (short) 104, (short) 105, (short) 106, (short) 107, (short) 108, 
					    (short) 109, (short) 110, (short) 111, (short) 112, (short) 113, (short) 114, (short) 115, 
					    (short) 116, (short) 117, (short) 118, (short) 119, (short) 120, (short) 121, (short) 122, 
					    (short) 123, (short) 124, (short) 125, (short) 126, (short) 127, (short) 128, (short) 129, 
					    (short) 130, (short) 131, (short) 132, (short) 133, (short) 134, (short) 135, (short) 136, 
					    (short) 137, (short) 138, (short) 139, (short) 140, (short) 141, (short) 142, (short) 143, 
					    (short) 144, (short) 145, (short) 146, (short) 147, (short) 148, (short) 149, (short) 150, 
					    (short) 151, (short) 152, (short) 153, (short) 154, (short) 155, (short) 156, (short) 157, 
					    (short) 158, (short) 159, (short) 160, (short) 161, (short) 162, (short) 163, (short) 164, 
					    (short) 165, (short) 166, (short) 167, (short) 168, (short) 169, (short) 170, (short) 171, 
					    (short) 172, (short) 173, (short) 174, (short) 175, (short) 176, (short) 177, (short) 178, 
					    (short) 179, (short) 180, (short) 181, (short) 182, (short) 183, (short) 184, (short) 185, 
					    (short) 186, (short) 187, (short) 188, (short) 189, (short) 190, (short) 191, (short) 192, 
					    (short) 193, (short) 194, (short) 195, (short) 196, (short) 197, (short) 198, (short) 199, 
					    (short) 200, (short) 201, (short) 202, (short) 203, (short) 204, (short) 205, (short) 206, 
					    (short) 207, (short) 208, (short) 209, (short) 210, (short) 211, (short) 212, (short) 213, 
					    (short) 214, (short) 215, (short) 216, (short) 217, (short) 218, (short) 219, (short) 220, 
					    (short) 221, (short) 222, (short) 223, (short) 224, (short) 225, (short) 226, (short) 227, 
					    (short) 228, (short) 229, (short) 230, (short) 231, (short) 232, (short) 233, (short) 234, 
					    (short) 235, (short) 236, (short) 237, (short) 238, (short) 239, (short) 240, (short) 241, 
					    (short) 242, (short) 243, (short) 244, (short) 245, (short) 246, (short) 247, (short) 248, 
					    (short) 249, (short) 250, (short) 251, (short) 252, (short) 253, (short) 254, (short) 255, 
					    (short) 256, (short) 257, (short) 258, (short) 259, (short) 260, (short) 261, (short) 262, 
					    (short) 263, (short) 264, (short) 265, (short) 266, (short) 267, (short) 268, (short) 269, 
					    (short) 270, (short) 271, (short) 272, (short) 273, (short) 274, (short) 275, (short) 276, 
					    (short) 277, (short) 278, (short) 279, (short) 280, (short) 281, (short) 282, (short) 283, 
					    (short) 284, (short) 285, (short) 286, (short) 287, (short) 288, (short) 289, (short) 290, 
					    (short) 291, (short) 292, (short) 293, (short) 294, (short) 295, (short) 296, (short) 297, 
					    (short) 298, (short) 299, (short) 300, (short) 301, (short) 302, (short) 303, (short) 304, 
					    (short) 305, (short) 306, (short) 307, (short) 308, (short) 309, (short) 310, (short) 311, 
					    (short) 312, (short) 313, (short) 314, (short) 315, (short) 316, (short) 317, (short) 318, 
					    (short) 319, (short) 320, (short) 321, (short) 322, (short) 323, (short) 324, (short) 325, 
					    (short) 326, (short) 327, (short) 328, (short) 329, (short) 330, (short) 331, (short) 332, 
					    (short) 333, (short) 334, (short) 335, (short) 336, (short) 337, (short) 338, (short) 339, 
					    (short) 340, (short) 341, (short) 342, (short) 343, (short) 344, (short) 345, (short) 346, 
					    (short) 347, (short) 348, (short) 349, (short) 350, (short) 351, (short) 352, (short) 353, 
					    (short) 354, (short) 355, (short) 356, (short) 357, (short) 358, (short) 359, (short) 360, 
					    (short) 361, (short) 362, (short) 363, (short) 364, (short) 365, (short) 366, (short) 367, 
					    (short) 368, (short) 369, (short) 370, (short) 371, (short) 372, (short) 373, (short) 374, 
					    (short) 375, (short) 376, (short) 377, (short) 378, (short) 379, (short) 380, (short) 381, 
					    (short) 382, (short) 383, (short) 384, (short) 385, (short) 386, (short) 387, (short) 388, 
					    (short) 389, (short) 390, (short) 391, (short) 392, (short) 393, (short) 394, (short) 395, 
					    (short) 396, (short) 397, (short) 398, (short) 399, (short) 400, (short) 401, (short) 402, 
					    (short) 403, (short) 404, (short) 405, (short) 406, (short) 407, (short) 408, (short) 409, 
					    (short) 410, (short) 411, (short) 412, (short) 413, (short) 414, (short) 415, (short) 416, 
					    (short) 417, (short) 418, (short) 419, (short) 420, (short) 421, (short) 422, (short) 423, 
					    (short) 424, (short) 425, (short) 426, (short) 427, (short) 428, (short) 429, (short) 430, 
					    (short) 431, (short) 432, (short) 433, (short) 434, (short) 435, (short) 436, (short) 437, 
					    (short) 438, (short) 439, (short) 440, (short) 441, (short) 442, (short) 443, (short) 444, 
					    (short) 445, (short) 446, (short) 447, (short) 448, (short) 449, (short) 450, (short) 451, 
					    (short) 452, (short) 453, (short) 454, (short) 455, (short) 456, (short) 457, (short) 458, 
					    (short) 459, (short) 460, (short) 461, (short) 462, (short) 463, (short) 464, (short) 465, 
					    (short) 466, (short) 467, (short) 468, (short) 469, (short) 470, (short) 471, (short) 472, 
					    (short) 473, (short) 474, (short) 475, (short) 476, (short) 477, (short) 478, (short) 479, 
					    (short) 480, (short) 481, (short) 482, (short) 483, (short) 484, (short) 485, (short) 486, 
					    (short) 487, (short) 488, (short) 489, (short) 490, (short) 491, (short) 492, (short) 493, 
					    (short) 494, (short) 495, (short) 496, (short) 497, (short) 498, (short) 499, (short) 500, 
					    (short) 501, (short) 502, (short) 503, (short) 504, (short) 505, (short) 506, (short) 507, 
					    (short) 508, (short) 509, (short) 510, (short) 511, (short) 1, (short) 2, (short) 3, 
					    (short) 4, (short) 5, (short) 6, (short) 7, (short) 8, (short) 9, (short) 10, 
					    (short) 11, (short) 12, (short) 13, (short) 14, (short) 15, (short) 16, (short) 17, 
					    (short) 18, (short) 19, (short) 20, (short) 21, (short) 22, (short) 23, (short) 24, 
					    (short) 25, (short) 26, (short) 27, (short) 28, (short) 29, (short) 30, (short) 31, 
					    (short) 0};

    

    public static short[] SHORT_CACHE_128 = {(short) 64, (short) 65, (short) 66, (short) 67, (short) 68, (short) 69, (short) 70, 
					     (short) 71, (short) 72, (short) 73, (short) 74, (short) 75, (short) 76, (short) 77, 
					     (short) 78, (short) 79, (short) 80, (short) 81, (short) 82, (short) 83, (short) 84, 
					     (short) 85, (short) 86, (short) 87, (short) 88, (short) 89, (short) 90, (short) 91, 
					     (short) 92, (short) 93, (short) 94, (short) 95, (short) 96, (short) 97, (short) 98, 
					     (short) 99, (short) 100, (short) 101, (short) 102, (short) 103, (short) 104, (short) 105, 
					     (short) 106, (short) 107, (short) 108, (short) 109, (short) 110, (short) 111, (short) 112, 
					     (short) 113, (short) 114, (short) 115, (short) 116, (short) 117, (short) 118, (short) 119, 
					     (short) 120, (short) 121, (short) 122, (short) 123, (short) 124, (short) 125, (short) 126, 
					     (short) 127, (short) 128, (short) 129, (short) 130, (short) 131, (short) 132, (short) 133, 
					     (short) 134, (short) 135, (short) 136, (short) 137, (short) 138, (short) 139, (short) 140, 
					     (short) 141, (short) 142, (short) 143, (short) 144, (short) 145, (short) 146, (short) 147, 
					     (short) 148, (short) 149, (short) 150, (short) 151, (short) 152, (short) 153, (short) 154, 
					     (short) 155, (short) 156, (short) 157, (short) 158, (short) 159, (short) 160, (short) 161, 
					     (short) 162, (short) 163, (short) 164, (short) 165, (short) 166, (short) 167, (short) 168, 
					     (short) 169, (short) 170, (short) 171, (short) 172, (short) 173, (short) 174, (short) 175, 
					     (short) 176, (short) 177, (short) 178, (short) 179, (short) 180, (short) 181, (short) 182, 
					     (short) 183, (short) 184, (short) 185, (short) 186, (short) 187, (short) 188, (short) 189, 
					     (short) 190, (short) 191, (short) 192, (short) 193, (short) 194, (short) 195, (short) 196, 
					     (short) 197, (short) 198, (short) 199, (short) 200, (short) 201, (short) 202, (short) 203, 
					     (short) 204, (short) 205, (short) 206, (short) 207, (short) 208, (short) 209, (short) 210, 
					     (short) 211, (short) 212, (short) 213, (short) 214, (short) 215, (short) 216, (short) 217, 
					     (short) 218, (short) 219, (short) 220, (short) 221, (short) 222, (short) 223, (short) 224, 
					     (short) 225, (short) 226, (short) 227, (short) 228, (short) 229, (short) 230, (short) 231, 
					     (short) 232, (short) 233, (short) 234, (short) 235, (short) 236, (short) 237, (short) 238, 
					     (short) 239, (short) 240, (short) 241, (short) 242, (short) 243, (short) 244, (short) 245, 
					     (short) 246, (short) 247, (short) 248, (short) 249, (short) 250, (short) 251, (short) 252, 
					     (short) 253, (short) 254, (short) 255, (short) 256, (short) 257, (short) 258, (short) 259, 
					     (short) 260, (short) 261, (short) 262, (short) 263, (short) 264, (short) 265, (short) 266, 
					     (short) 267, (short) 268, (short) 269, (short) 270, (short) 271, (short) 272, (short) 273, 
					     (short) 274, (short) 275, (short) 276, (short) 277, (short) 278, (short) 279, (short) 280, 
					     (short) 281, (short) 282, (short) 283, (short) 284, (short) 285, (short) 286, (short) 287, 
					     (short) 288, (short) 289, (short) 290, (short) 291, (short) 292, (short) 293, (short) 294, 
					     (short) 295, (short) 296, (short) 297, (short) 298, (short) 299, (short) 300, (short) 301, 
					     (short) 302, (short) 303, (short) 304, (short) 305, (short) 306, (short) 307, (short) 308, 
					     (short) 309, (short) 310, (short) 311, (short) 312, (short) 313, (short) 314, (short) 315, 
					     (short) 316, (short) 317, (short) 318, (short) 319, (short) 320, (short) 321, (short) 322, 
					     (short) 323, (short) 324, (short) 325, (short) 326, (short) 327, (short) 328, (short) 329, 
					     (short) 330, (short) 331, (short) 332, (short) 333, (short) 334, (short) 335, (short) 336, 
					     (short) 337, (short) 338, (short) 339, (short) 340, (short) 341, (short) 342, (short) 343, 
					     (short) 344, (short) 345, (short) 346, (short) 347, (short) 348, (short) 349, (short) 350, 
					     (short) 351, (short) 352, (short) 353, (short) 354, (short) 355, (short) 356, (short) 357, 
					     (short) 358, (short) 359, (short) 360, (short) 361, (short) 362, (short) 363, (short) 364, 
					     (short) 365, (short) 366, (short) 367, (short) 368, (short) 369, (short) 370, (short) 371, 
					     (short) 372, (short) 373, (short) 374, (short) 375, (short) 376, (short) 377, (short) 378, 
					     (short) 379, (short) 380, (short) 381, (short) 382, (short) 383, (short) 384, (short) 385, 
					     (short) 386, (short) 387, (short) 388, (short) 389, (short) 390, (short) 391, (short) 392, 
					     (short) 393, (short) 394, (short) 395, (short) 396, (short) 397, (short) 398, (short) 399, 
					     (short) 400, (short) 401, (short) 402, (short) 403, (short) 404, (short) 405, (short) 406, 
					     (short) 407, (short) 408, (short) 409, (short) 410, (short) 411, (short) 412, (short) 413, 
					     (short) 414, (short) 415, (short) 416, (short) 417, (short) 418, (short) 419, (short) 420, 
					     (short) 421, (short) 422, (short) 423, (short) 424, (short) 425, (short) 426, (short) 427, 
					     (short) 428, (short) 429, (short) 430, (short) 431, (short) 432, (short) 433, (short) 434, 
					     (short) 435, (short) 436, (short) 437, (short) 438, (short) 439, (short) 440, (short) 441, 
					     (short) 442, (short) 443, (short) 444, (short) 445, (short) 446, (short) 447, (short) 448, 
					     (short) 449, (short) 450, (short) 451, (short) 452, (short) 453, (short) 454, (short) 455, 
					     (short) 456, (short) 457, (short) 458, (short) 459, (short) 460, (short) 461, (short) 462, 
					     (short) 463, (short) 464, (short) 465, (short) 466, (short) 467, (short) 468, (short) 469, 
					     (short) 470, (short) 471, (short) 472, (short) 473, (short) 474, (short) 475, (short) 476, 
					     (short) 477, (short) 478, (short) 479, (short) 480, (short) 481, (short) 482, (short) 483, 
					     (short) 484, (short) 485, (short) 486, (short) 487, (short) 488, (short) 489, (short) 490, 
					     (short) 491, (short) 492, (short) 493, (short) 494, (short) 495, (short) 496, (short) 497, 
					     (short) 498, (short) 499, (short) 500, (short) 501, (short) 502, (short) 503, (short) 504, 
					     (short) 505, (short) 506, (short) 507, (short) 508, (short) 509, (short) 510, (short) 511, 
					     (short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7, 
					     (short) 8, (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14, 
					     (short) 15, (short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21, 
					     (short) 22, (short) 23, (short) 24, (short) 25, (short) 26, (short) 27, (short) 28, 
					     (short) 29, (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35, 
					     (short) 36, (short) 37, (short) 38, (short) 39, (short) 40, (short) 41, (short) 42, 
					     (short) 43, (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49, 
					     (short) 50, (short) 51, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56, 
					     (short) 57, (short) 58, (short) 59, (short) 60, (short) 61, (short) 62, (short) 63, 
					     (short) 0};
    
    public static short[] SHORT_CACHE_256 = {(short) 256, (short) 257, (short) 258, (short) 259, (short) 260, (short) 261, (short) 262,
    	(short) 263, (short) 264, (short) 265, (short) 266, (short) 267, (short) 268, (short) 269,
    	(short) 270, (short) 271, (short) 272, (short) 273, (short) 274, (short) 275, (short) 276,
    	(short) 277, (short) 278, (short) 279, (short) 280, (short) 281, (short) 282, (short) 283,
    	(short) 284, (short) 285, (short) 286, (short) 287, (short) 288, (short) 289, (short) 290,
    	(short) 291, (short) 292, (short) 293, (short) 294, (short) 295, (short) 296, (short) 297,
    	(short) 298, (short) 299, (short) 300, (short) 301, (short) 302, (short) 303, (short) 304,
    	(short) 305, (short) 306, (short) 307, (short) 308, (short) 309, (short) 310, (short) 311,
    	(short) 312, (short) 313, (short) 314, (short) 315, (short) 316, (short) 317, (short) 318,
    	(short) 319, (short) 320, (short) 321, (short) 322, (short) 323, (short) 324, (short) 325,
    	(short) 326, (short) 327, (short) 328, (short) 329, (short) 330, (short) 331, (short) 332,
    	(short) 333, (short) 334, (short) 335, (short) 336, (short) 337, (short) 338, (short) 339,
    	(short) 340, (short) 341, (short) 342, (short) 343, (short) 344, (short) 345, (short) 346,
    	(short) 347, (short) 348, (short) 349, (short) 350, (short) 351, (short) 352, (short) 353,
    	(short) 354, (short) 355, (short) 356, (short) 357, (short) 358, (short) 359, (short) 360,
    	(short) 361, (short) 362, (short) 363, (short) 364, (short) 365, (short) 366, (short) 367,
    	(short) 368, (short) 369, (short) 370, (short) 371, (short) 372, (short) 373, (short) 374,
    	(short) 375, (short) 376, (short) 377, (short) 378, (short) 379, (short) 380, (short) 381,
    	(short) 382, (short) 383, (short) 384, (short) 385, (short) 386, (short) 387, (short) 388,
    	(short) 389, (short) 390, (short) 391, (short) 392, (short) 393, (short) 394, (short) 395,
    	(short) 396, (short) 397, (short) 398, (short) 399, (short) 400, (short) 401, (short) 402,
    	(short) 403, (short) 404, (short) 405, (short) 406, (short) 407, (short) 408, (short) 409,
    	(short) 410, (short) 411, (short) 412, (short) 413, (short) 414, (short) 415, (short) 416,
    	(short) 417, (short) 418, (short) 419, (short) 420, (short) 421, (short) 422, (short) 423,
    	(short) 424, (short) 425, (short) 426, (short) 427, (short) 428, (short) 429, (short) 430,
    	(short) 431, (short) 432, (short) 433, (short) 434, (short) 435, (short) 436, (short) 437,
    	(short) 438, (short) 439, (short) 440, (short) 441, (short) 442, (short) 443, (short) 444,
    	(short) 445, (short) 446, (short) 447, (short) 448, (short) 449, (short) 450, (short) 451,
    	(short) 452, (short) 453, (short) 454, (short) 455, (short) 456, (short) 457, (short) 458,
    	(short) 459, (short) 460, (short) 461, (short) 462, (short) 463, (short) 464, (short) 465,
    	(short) 466, (short) 467, (short) 468, (short) 469, (short) 470, (short) 471, (short) 472,
    	(short) 473, (short) 474, (short) 475, (short) 476, (short) 477, (short) 478, (short) 479,
    	(short) 480, (short) 481, (short) 482, (short) 483, (short) 484, (short) 485, (short) 486,
    	(short) 487, (short) 488, (short) 489, (short) 490, (short) 491, (short) 492, (short) 493,
    	(short) 494, (short) 495, (short) 496, (short) 497, (short) 498, (short) 499, (short) 500,
    	(short) 501, (short) 502, (short) 503, (short) 504, (short) 505, (short) 506, (short) 507,
    	(short) 508, (short) 509, (short) 510, (short) 511, (short) 1, (short) 2, (short) 3,
    	(short) 4, (short) 5, (short) 6, (short) 7, (short) 8, (short) 9, (short) 10,
    	(short) 11, (short) 12, (short) 13, (short) 14, (short) 15, (short) 16, (short) 17,
    	(short) 18, (short) 19, (short) 20, (short) 21, (short) 22, (short) 23, (short) 24,
    	(short) 25, (short) 26, (short) 27, (short) 28, (short) 29, (short) 30, (short) 31,
    	(short) 32, (short) 33, (short) 34, (short) 35, (short) 36, (short) 37, (short) 38,
    	(short) 39, (short) 40, (short) 41, (short) 42, (short) 43, (short) 44, (short) 45,
    	(short) 46, (short) 47, (short) 48, (short) 49, (short) 50, (short) 51, (short) 52,
    	(short) 53, (short) 54, (short) 55, (short) 56, (short) 57, (short) 58, (short) 59,
    	(short) 60, (short) 61, (short) 62, (short) 63, (short) 64, (short) 65, (short) 66,
    	(short) 67, (short) 68, (short) 69, (short) 70, (short) 71, (short) 72, (short) 73,
    	(short) 74, (short) 75, (short) 76, (short) 77, (short) 78, (short) 79, (short) 80,
    	(short) 81, (short) 82, (short) 83, (short) 84, (short) 85, (short) 86, (short) 87,
    	(short) 88, (short) 89, (short) 90, (short) 91, (short) 92, (short) 93, (short) 94,
    	(short) 95, (short) 96, (short) 97, (short) 98, (short) 99, (short) 100, (short) 101,
    	(short) 102, (short) 103, (short) 104, (short) 105, (short) 106, (short) 107, (short) 108,
    	(short) 109, (short) 110, (short) 111, (short) 112, (short) 113, (short) 114, (short) 115,
    	(short) 116, (short) 117, (short) 118, (short) 119, (short) 120, (short) 121, (short) 122,
    	(short) 123, (short) 124, (short) 125, (short) 126, (short) 127, (short) 128, (short) 129,
    	(short) 130, (short) 131, (short) 132, (short) 133, (short) 134, (short) 135, (short) 136,
    	(short) 137, (short) 138, (short) 139, (short) 140, (short) 141, (short) 142, (short) 143,
    	(short) 144, (short) 145, (short) 146, (short) 147, (short) 148, (short) 149, (short) 150,
    	(short) 151, (short) 152, (short) 153, (short) 154, (short) 155, (short) 156, (short) 157,
    	(short) 158, (short) 159, (short) 160, (short) 161, (short) 162, (short) 163, (short) 164,
    	(short) 165, (short) 166, (short) 167, (short) 168, (short) 169, (short) 170, (short) 171,
    	(short) 172, (short) 173, (short) 174, (short) 175, (short) 176, (short) 177, (short) 178,
    	(short) 179, (short) 180, (short) 181, (short) 182, (short) 183, (short) 184, (short) 185,
    	(short) 186, (short) 187, (short) 188, (short) 189, (short) 190, (short) 191, (short) 192,
    	(short) 193, (short) 194, (short) 195, (short) 196, (short) 197, (short) 198, (short) 199,
    	(short) 200, (short) 201, (short) 202, (short) 203, (short) 204, (short) 205, (short) 206,
    	(short) 207, (short) 208, (short) 209, (short) 210, (short) 211, (short) 212, (short) 213,
    	(short) 214, (short) 215, (short) 216, (short) 217, (short) 218, (short) 219, (short) 220,
    	(short) 221, (short) 222, (short) 223, (short) 224, (short) 225, (short) 226, (short) 227,
    	(short) 228, (short) 229, (short) 230, (short) 231, (short) 232, (short) 233, (short) 234,
    	(short) 235, (short) 236, (short) 237, (short) 238, (short) 239, (short) 240, (short) 241,
    	(short) 242, (short) 243, (short) 244, (short) 245, (short) 246, (short) 247, (short) 248,
    	(short) 249, (short) 250, (short) 251, (short) 252, (short) 253, (short) 254, (short) 255,
    	(short) 0};
    
    public static short[] SHORT_CACHE_512 = {(short) 256, (short) 257, (short) 258, (short) 259, (short) 260, (short) 261, (short) 262, 
					     (short) 263, (short) 264, (short) 265, (short) 266, (short) 267, (short) 268, (short) 269, 
					     (short) 270, (short) 271, (short) 272, (short) 273, (short) 274, (short) 275, (short) 276, 
					     (short) 277, (short) 278, (short) 279, (short) 280, (short) 281, (short) 282, (short) 283, 
					     (short) 284, (short) 285, (short) 286, (short) 287, (short) 288, (short) 289, (short) 290, 
					     (short) 291, (short) 292, (short) 293, (short) 294, (short) 295, (short) 296, (short) 297, 
					     (short) 298, (short) 299, (short) 300, (short) 301, (short) 302, (short) 303, (short) 304, 
					     (short) 305, (short) 306, (short) 307, (short) 308, (short) 309, (short) 310, (short) 311, 
					     (short) 312, (short) 313, (short) 314, (short) 315, (short) 316, (short) 317, (short) 318, 
					     (short) 319, (short) 320, (short) 321, (short) 322, (short) 323, (short) 324, (short) 325, 
					     (short) 326, (short) 327, (short) 328, (short) 329, (short) 330, (short) 331, (short) 332, 
					     (short) 333, (short) 334, (short) 335, (short) 336, (short) 337, (short) 338, (short) 339, 
					     (short) 340, (short) 341, (short) 342, (short) 343, (short) 344, (short) 345, (short) 346, 
					     (short) 347, (short) 348, (short) 349, (short) 350, (short) 351, (short) 352, (short) 353, 
					     (short) 354, (short) 355, (short) 356, (short) 357, (short) 358, (short) 359, (short) 360, 
					     (short) 361, (short) 362, (short) 363, (short) 364, (short) 365, (short) 366, (short) 367, 
					     (short) 368, (short) 369, (short) 370, (short) 371, (short) 372, (short) 373, (short) 374, 
					     (short) 375, (short) 376, (short) 377, (short) 378, (short) 379, (short) 380, (short) 381, 
					     (short) 382, (short) 383, (short) 384, (short) 385, (short) 386, (short) 387, (short) 388, 
					     (short) 389, (short) 390, (short) 391, (short) 392, (short) 393, (short) 394, (short) 395, 
					     (short) 396, (short) 397, (short) 398, (short) 399, (short) 400, (short) 401, (short) 402, 
					     (short) 403, (short) 404, (short) 405, (short) 406, (short) 407, (short) 408, (short) 409, 
					     (short) 410, (short) 411, (short) 412, (short) 413, (short) 414, (short) 415, (short) 416, 
					     (short) 417, (short) 418, (short) 419, (short) 420, (short) 421, (short) 422, (short) 423, 
					     (short) 424, (short) 425, (short) 426, (short) 427, (short) 428, (short) 429, (short) 430, 
					     (short) 431, (short) 432, (short) 433, (short) 434, (short) 435, (short) 436, (short) 437, 
					     (short) 438, (short) 439, (short) 440, (short) 441, (short) 442, (short) 443, (short) 444, 
					     (short) 445, (short) 446, (short) 447, (short) 448, (short) 449, (short) 450, (short) 451, 
					     (short) 452, (short) 453, (short) 454, (short) 455, (short) 456, (short) 457, (short) 458, 
					     (short) 459, (short) 460, (short) 461, (short) 462, (short) 463, (short) 464, (short) 465, 
					     (short) 466, (short) 467, (short) 468, (short) 469, (short) 470, (short) 471, (short) 472, 
					     (short) 473, (short) 474, (short) 475, (short) 476, (short) 477, (short) 478, (short) 479, 
					     (short) 480, (short) 481, (short) 482, (short) 483, (short) 484, (short) 485, (short) 486, 
					     (short) 487, (short) 488, (short) 489, (short) 490, (short) 491, (short) 492, (short) 493, 
					     (short) 494, (short) 495, (short) 496, (short) 497, (short) 498, (short) 499, (short) 500, 
					     (short) 501, (short) 502, (short) 503, (short) 504, (short) 505, (short) 506, (short) 507, 
					     (short) 508, (short) 509, (short) 510, (short) 511, (short) 1, (short) 2, (short) 3, 
					     (short) 4, (short) 5, (short) 6, (short) 7, (short) 8, (short) 9, (short) 10, 
					     (short) 11, (short) 12, (short) 13, (short) 14, (short) 15, (short) 16, (short) 17, 
					     (short) 18, (short) 19, (short) 20, (short) 21, (short) 22, (short) 23, (short) 24, 
					     (short) 25, (short) 26, (short) 27, (short) 28, (short) 29, (short) 30, (short) 31, 
					     (short) 32, (short) 33, (short) 34, (short) 35, (short) 36, (short) 37, (short) 38, 
					     (short) 39, (short) 40, (short) 41, (short) 42, (short) 43, (short) 44, (short) 45, 
					     (short) 46, (short) 47, (short) 48, (short) 49, (short) 50, (short) 51, (short) 52, 
					     (short) 53, (short) 54, (short) 55, (short) 56, (short) 57, (short) 58, (short) 59, 
					     (short) 60, (short) 61, (short) 62, (short) 63, (short) 64, (short) 65, (short) 66, 
					     (short) 67, (short) 68, (short) 69, (short) 70, (short) 71, (short) 72, (short) 73, 
					     (short) 74, (short) 75, (short) 76, (short) 77, (short) 78, (short) 79, (short) 80, 
					     (short) 81, (short) 82, (short) 83, (short) 84, (short) 85, (short) 86, (short) 87, 
					     (short) 88, (short) 89, (short) 90, (short) 91, (short) 92, (short) 93, (short) 94, 
					     (short) 95, (short) 96, (short) 97, (short) 98, (short) 99, (short) 100, (short) 101, 
					     (short) 102, (short) 103, (short) 104, (short) 105, (short) 106, (short) 107, (short) 108, 
					     (short) 109, (short) 110, (short) 111, (short) 112, (short) 113, (short) 114, (short) 115, 
					     (short) 116, (short) 117, (short) 118, (short) 119, (short) 120, (short) 121, (short) 122, 
					     (short) 123, (short) 124, (short) 125, (short) 126, (short) 127, (short) 128, (short) 129, 
					     (short) 130, (short) 131, (short) 132, (short) 133, (short) 134, (short) 135, (short) 136, 
					     (short) 137, (short) 138, (short) 139, (short) 140, (short) 141, (short) 142, (short) 143, 
					     (short) 144, (short) 145, (short) 146, (short) 147, (short) 148, (short) 149, (short) 150, 
					     (short) 151, (short) 152, (short) 153, (short) 154, (short) 155, (short) 156, (short) 157, 
					     (short) 158, (short) 159, (short) 160, (short) 161, (short) 162, (short) 163, (short) 164, 
					     (short) 165, (short) 166, (short) 167, (short) 168, (short) 169, (short) 170, (short) 171, 
					     (short) 172, (short) 173, (short) 174, (short) 175, (short) 176, (short) 177, (short) 178, 
					     (short) 179, (short) 180, (short) 181, (short) 182, (short) 183, (short) 184, (short) 185, 
					     (short) 186, (short) 187, (short) 188, (short) 189, (short) 190, (short) 191, (short) 192, 
					     (short) 193, (short) 194, (short) 195, (short) 196, (short) 197, (short) 198, (short) 199, 
					     (short) 200, (short) 201, (short) 202, (short) 203, (short) 204, (short) 205, (short) 206, 
					     (short) 207, (short) 208, (short) 209, (short) 210, (short) 211, (short) 212, (short) 213, 
					     (short) 214, (short) 215, (short) 216, (short) 217, (short) 218, (short) 219, (short) 220, 
					     (short) 221, (short) 222, (short) 223, (short) 224, (short) 225, (short) 226, (short) 227, 
					     (short) 228, (short) 229, (short) 230, (short) 231, (short) 232, (short) 233, (short) 234, 
					     (short) 235, (short) 236, (short) 237, (short) 238, (short) 239, (short) 240, (short) 241, 
					     (short) 242, (short) 243, (short) 244, (short) 245, (short) 246, (short) 247, (short) 248, 
					     (short) 249, (short) 250, (short) 251, (short) 252, (short) 253, (short) 254, (short) 255, 
					     (short) 0};

    public static short iterCount = 0;

	
    /**x
     * 
     */
    ReadCacheApplet() {
	super();
    }


    /**
     * @see javacard.framework.Applet#install(byte[], short, byte)
     */
    public static void install(byte[] bArray, short bOffset, byte bLength) {
	new ReadCacheApplet().register();
    }	    


    /**
     * @see Test#getTestCases()
     */
    public TestCase[] getTestCases() {
	return new TestCase[] {
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    test_unit_ref();
		}
	    },
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    test_unit_cache_2();
		}
	    },
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    test_unit_cache_4();
		}
	    },
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    test_unit_cache_8();
		}
	    },
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    test_unit_cache_16();
		}
	    },
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    test_unit_cache_32();
		}
	    },
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    test_unit_cache_64();
		}
	    },
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    test_unit_cache_128();
		}
	    },
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    test_unit_cache_256();
		}
	    },
	    new ReadCacheTestCase() {
			public void run(byte[] apduBuffer) {
			    test_unit_cache_512();
			}
		    },
	    new ReadCacheTestCase() {
		public void run(byte[] apduBuffer) {
		    clean();
		}
	    }
	};
    }
     
    void test_unit_ref(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;
	short[] L = SHORT_REF;
	for(;l0<l1;l0++){
	    nothing = L[nothing];
	}
    }  
  

    /** Test reading array for 2 bytes cache detection */
    void test_unit_cache_2(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;
	short[] L = SHORT_CACHE_2;
	for(;l0<l1;l0++){
	    nothing = L[nothing];
	}
    }    

    /** Test reading array for 4 bytes cache detection */
    void test_unit_cache_4(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;
	short[] L = SHORT_CACHE_4;
	for(;l0<l1;l0++){
	    nothing = L[nothing];
	}
    }    

    /** Test reading array for 8 bytes cache detection */
    void test_unit_cache_8(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;
	short[] L = SHORT_CACHE_8;
	for(;l0<l1;l0++){
	    nothing = L[nothing];
	}
    }    

    /** Test reading array for 16 bytes cache detection */
    void test_unit_cache_16(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;

	short[] L = SHORT_CACHE_16;
	for(;l0<l1;l0++){
	    nothing = L[nothing];
	}
    }    

    /** Test reading array for 32 bytes cache detection */
    void test_unit_cache_32(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;
	short[] L = SHORT_CACHE_32;
	for(;l0<l1;l0++){
	    nothing = L[nothing];
	}
    }    
    
    /** Test reading array for 64 bytes cache detection */
    void test_unit_cache_64(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;
	short[] L = SHORT_CACHE_64;
	for(;l0<l1;l0++){
	    nothing = L[nothing];

	}
    }    

    /** Test reading array for 128 bytes cache detection */
    void test_unit_cache_128(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;
	short[] L = SHORT_CACHE_128;
	for(;l0<l1;l0++){
	    nothing = L[nothing];
	}
    }    

    /** Test reading array for 256 bytes cache detection */
    void test_unit_cache_256(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;
	short[] L = SHORT_CACHE_256;
	for(;l0<l1;l0++){
	    nothing = L[nothing];
	}
    }    
    
    /** Test reading array for 512 bytes cache detection */
    void test_unit_cache_512(){
	short l0=0;
	short l1 = iterCount;
	short nothing = 0;
	short[] L = SHORT_CACHE_512;
	for(;l0<l1;l0++){
	    nothing = L[nothing];
	}
    }
    
    /** clean all static references */
    void clean(){
	SHORT_REF = null;
	SHORT_CACHE_2 = null;
	SHORT_CACHE_4 = null;
	SHORT_CACHE_8 = null;
	SHORT_CACHE_16 = null;
	SHORT_CACHE_32 = null;
	SHORT_CACHE_64 = null;
	SHORT_CACHE_128 = null;
	SHORT_CACHE_256 = null;
	SHORT_CACHE_512 = null;
    }
    
}

class ReadCacheTestCase extends TestCase{

    public ReadCacheTestCase() {
	super();
	setUseInnerCounter(true);
    }
    public void setUp(byte[] buffer, short iterCount) {
	ReadCacheApplet.iterCount = iterCount;					     
    }

    public void cleanUp(byte[] buffer){
	;
    }

    public void run(byte[] buffer){
	;
    }

}

