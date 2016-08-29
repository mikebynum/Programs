;LISP - Program 2
;CSCI 4220
;Due 3-30-12
;Mike Bynum

(load 'domino_data.lsp)



;The ever-popular data set
;(setq data '( ((0 0 b) (1 0 b))  ((0 1 3) (0 2 2))  ((0 3 b) (0 4 4)) ((0 5 4) (0 6 5))
;              ((1 1 5) (2 1 2))  ((1 2 3) (1 3 5))  ((1 4 b) (2 4 6)) ((1 5 6) (1 6 2))
;              ((2 0 4) (3 0 2))  ((2 2 1) (2 3 2))  ((2 5 6) (3 5 3)) ((2 6 4) (3 6 6))
;              ((3 1 4) (3 2 4))  ((3 3 1) (3 4 1))  ((4 0 b) (4 1 3)) ((4 2 1) (5 2 b))
;              ((4 3 4) (5 3 3))  ((4 4 5) (4 5 1))  ((4 6 6) (5 6 1)) ((5 0 2) (5 1 2))
;              ((5 4 4) (6 4 1))  ((5 5 5) (6 5 5))  ((6 0 5) (7 0 6)) ((6 1 1) (6 2 3))
;              ((6 3 3) (7 3 3))  ((6 6 6) (7 6 6))  ((7 1 b) (7 2 5)) ((7 4 2) (7 5 b)) ) )

(setq begin '((0 0)))

(defun get-value (R C data)
        (cond ((null data) (format t "Seems to be a mistake in get-value...~s ~s ~%" R C) 
                nil)
        (T  (let* ((dom(first data))
                   (left (first dom))
                   (right (second dom)))
            (cond ((and (= R (first left)) (= C (second left)))
                  (third left))
                  ((and (= R (first right)) (= C (second right)))
                  (third right))
            (T
                  (get-value R C (rest data)))
               )))))

(defun same-row (R C data)
        (let* ((X (get-value R C data))
          (retvalue nil))
        ;(print X)
        (dotimes(col 7)    
                (cond ((and (eq X (get-value R col data)) (not (= col C)))
                      (setq retvalue (cons (list R col) retvalue))
                      ))
         )
          retvalue     
        ))

(defun same-column (R C data)
        (let* ((X (get-value R C data))
          (retvalue1 nil))
        ;(print X)
        (dotimes(row 8)    
                (cond ((eq X (get-value row C data)) 
                      (setq retvalue1 (cons (list row c) retvalue1))
                      ))
         )
          retvalue1    
        ))

(defun opposite (R C data)
        (cond ((null data) (format t "Seems to be a mistake in opposite... ~s ~s ~%" R C )
                nil)
        (T (let* ((dom(first data))
                  (left (first dom))
                  (right (second dom))
                   (opp nil))
           (cond ((and (= R (first left)) (= C (second left)))
                 (setq opp (cons (list (first right) (second right)) opp )))
          ((and (= R (first right)) (= C (second right)))
                 (setq opp (cons (list (first left) (second left)) opp )))
           (T
                 (opposite R C (rest data)))
           )))))

(defun move (path)
  (let* ((currpos (first path))
    (R (first currpos))
    (C (second currpos))
    (possible_moves nil))
  (cond ((and (= R 7) (= C 6))
      (dolist (var (reverse path))
          (format t "Move: ~s, ~s ~%" (first var) (second var))) (format t "DONE! ~% ~%"))   
    ((member currpos (rest path) :test #'equal))
    (T
      (setq possible_moves (remove-duplicates (append (same-row R C data) (same-column R C data) (opposite R C data)) :test #'equal))
      (dolist (var possible_moves) (move (cons var path)))
      
      )))
 )

      
(defun doit ()
  (move begin)
    )

(doit)
(exit)

