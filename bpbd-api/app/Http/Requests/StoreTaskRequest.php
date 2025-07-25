<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreTaskRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'title' => 'required|string|max:255',
            'location' => 'required|string|max:255',
            'disaster_type' => 'required|string|max:255',
            'created_by' => 'required|exists:members,id',
            'member_ids' => 'sometimes|array',
            'member_ids.*' => 'exists:members,id',
        ];
    }
}
