package dynPackage;

public interface DynArray<E>  {
    // Query Operations

    /**
     * Returns the number of elements in this list.  If this list contains
     * more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    boolean contains(E e);



    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)

    // Modification Operations

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the {@code add} operation
     *         is not supported by this list
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this list
     */
    boolean add(E e);

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the {@code remove} operation
     *         is not supported by this list
     */
    boolean remove(E e);


    // Bulk Modification Operations





    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation
     *         is not supported by this list
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified object with this list for equality.  Returns
     * {@code true} if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements {@code e1} and
     * {@code e2} are <i>equal</i> if {@code Objects.equals(e1, e2)}.)
     * In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.  This
     * definition ensures that the equals method works properly across
     * different implementations of the {@code List} interface.
     *
     * @param o the object to be compared for equality with this list
     * @param e
     * @return {@code true} if the specified object is equal to this list
     */
    boolean equals(Object e);

    /**
     * Returns the hash code value for this list.  The hash code of a list
     * is defined to be the result of the following calculation:
     * <pre>{@code
     *     int hashCode = 1;
     *     for (E e : list)
     *         hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
     * }</pre>
     * This ensures that {@code list1.equals(list2)} implies that
     * {@code list1.hashCode()==list2.hashCode()} for any two lists,
     * {@code list1} and {@code list2}, as required by the general
     * contract of {@link Object#hashCode}.
     *
     * @return the hash code value for this list
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    int hashCode();


    // Positional Access Operations

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the {@code set} operation
     *         is not supported by this list
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and
     *         this list does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    E set(int index, E element);

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnsupportedOperationException if the {@code add} operation
     *         is not supported by this list
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws NullPointerException if the specified element is null and
     *         this list does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *         element prevents it from being added to this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index > size()})
     */
    void add(int index, E element);

    void addAll(E[] newArr);

    void addByCount(E[] newArr, int index, int count);

    void addAllByPosition(int position, E[] newArr);



    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the {@code remove} operation
     *         is not supported by this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     */
    E remove(int index);


    // Search Operations

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    int indexOf(E e);

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    int lastIndexOf(E e);


    // List Iterators

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in this list (in proper
     *         sequence)
     */

    // View





}
